import React from 'react';
import './App.css';
import TelemetryChart from './components/TelemetryChart';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <h1>Telemetry Dashboard</h1>
            </header>
            <TelemetryChart />
        </div>
    );
}

export default App;
