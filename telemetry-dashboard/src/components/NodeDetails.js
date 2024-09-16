import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';

const NodeDetails = () => {
    const {node} = useParams();
    const [nodeData, setNodeData] = useState([]);
    const API_URL = `http://127.0.0.1:8123/corpag/showtelemetry?node=${node}`;

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

    return (
        <div>
            <h2>Node: {node}</h2>
            <pre>{JSON.stringify(nodeData, null, 2)}</pre>
        </div>
    );
};

export default NodeDetails;
