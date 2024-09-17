// NodeDetails.js
import React, { useEffect, useState } from 'react';
import './NodeDetails.css'; // Import the new CSS file
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import { useParams } from 'react-router-dom';

const NodeDetails = () => {
    const { node } = useParams();
    const [nodeData, setNodeData] = useState([]);
    console.log(node);
    const API_URL = `http://127.0.0.1:8123/corpag/showtelemetry?nodeName=${node}`;

    useEffect(() => {
        fetch(API_URL)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => setNodeData(data))
            .catch(error => console.error('Error fetching node data:', error));
    }, [node]);

    // Format data to be used in the chart
    const formatData = (data) => {
        return data.map(entry => ({
            timestamp: new Date(entry.timestamp).toLocaleTimeString(),
            temperature: entry.temperature,
            networkSpeed: entry.networkSpeed,
            diskUtilization: entry.diskUtilization,
            cpuUtilization: entry.cpuUtilization,
        }));
    };

    const formattedData = formatData(nodeData);

    return (
        <div className="node-details-container">
            <h2>Node: {node}</h2>
            <ResponsiveContainer width="100%" height={500}>
                <LineChart data={formattedData}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="timestamp" />
                    <YAxis />
                    <Tooltip />
                    <Legend />
                    <Line type="monotone" dataKey="temperature" stroke="#8884d8" name="Temperature" />
                    <Line type="monotone" dataKey="networkSpeed" stroke="#82ca9d" name="Network Speed" />
                    <Line type="monotone" dataKey="diskUtilization" stroke="#ffc658" name="Disk Utilization" />
                    <Line type="monotone" dataKey="cpuUtilization" stroke="#ff7300" name="CPU Utilization" />
                </LineChart>
            </ResponsiveContainer>
        </div>
    );
};

export default NodeDetails;
