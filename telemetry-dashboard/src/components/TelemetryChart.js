import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Updated import
import { Bar, BarChart, CartesianGrid, Legend, ResponsiveContainer, Tooltip, XAxis, YAxis } from 'recharts';
import './TelemetryChart.css';

const TelemetryChart = () => {
    const [latestDataByNode, setLatestDataByNode] = useState({});
    const API_URL = 'http://127.0.0.1:8123/corpag/showtelemetry';
    const navigate = useNavigate(); // Updated to useNavigate

    useEffect(() => {
        const fetchTelemetryData = () => {
            fetch(API_URL)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    const latestData = {};
                    data.forEach(item => {
                        if (!latestData[item.node] || new Date(item.timestamp) > new Date(latestData[item.node].timestamp)) {
                            latestData[item.node] = item;
                        }
                    });
                    setLatestDataByNode(latestData);
                })
                .catch(error => console.error('Error fetching telemetry data:', error));
        };

        fetchTelemetryData(); // Initial fetch
        const interval = setInterval(fetchTelemetryData, 1000); // Fetch every 1 second

        return () => clearInterval(interval); // Cleanup interval on component unmount
    }, []);

    const handleNodeClick = (node) => {
        navigate(`/node/${node}`); // Updated to use navigate
    };

    return (
        <div className="telemetry-container">
            {Object.keys(latestDataByNode).map(node => (
                <div key={node} className="node-section">
                    <button onClick={() => handleNodeClick(node)}>Node: {node}</button>
                    <ResponsiveContainer width="100%" height={200}>
                        <BarChart data={[latestDataByNode[node]]} layout="vertical">
                            <CartesianGrid strokeDasharray="3 3" />
                            <XAxis type="number" />
                            <YAxis type="category" dataKey="timestamp"
                                   tickFormatter={(tick) => new Date(tick).toLocaleTimeString()} />
                            <Tooltip labelFormatter={(label) => new Date(label).toLocaleString()} />
                            <Legend />
                            <Bar dataKey="temperature" fill="#8884d8" name="Temperature" />
                            <Bar dataKey="networkSpeed" fill="#82ca9d" name="Network Speed" />
                            <Bar dataKey="diskUtilization" fill="#ffc658" name="Disk Utilization" />
                            <Bar dataKey="cpuUtilization" fill="#ff7300" name="CPU Utilization" />
                        </BarChart>
                    </ResponsiveContainer>
                </div>
            ))}
        </div>
    );
};

export default TelemetryChart;
