import React, { useEffect, useState } from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import './TelemetryChart.css';

const TelemetryChart = () => {
    const [latestDataByNode, setLatestDataByNode] = useState({});

    useEffect(() => {
        const fetchTelemetryData = () => {
            fetch('http://127.0.0.1:8124/corpag/telemetry')
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
        const interval = setInterval(fetchTelemetryData, 30000); // Fetch every 30 seconds

        return () => clearInterval(interval); // Cleanup interval on component unmount
    }, []);

    return (
        <div className="telemetry-container">
            {Object.keys(latestDataByNode).map(node => (
                <div key={node} className="node-section">
                    <h3>Node: {node}</h3>
                    <ResponsiveContainer width="100%" height={200}>
                        <BarChart data={[latestDataByNode[node]]} layout="vertical">
                            <CartesianGrid strokeDasharray="3 3" />
                            <XAxis type="number" />
                            <YAxis type="category" dataKey="timestamp" tickFormatter={(tick) => new Date(tick).toLocaleTimeString()} />
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
