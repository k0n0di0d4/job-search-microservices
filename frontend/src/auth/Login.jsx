import { useState } from "react";

export default function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    function handleSubmit(e) {
        e.preventDefault();

        fetch('http://localhost:8080/auth/authenticate', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({username, password})
        })
            .then(async res => {
                if (!res.ok) throw new Error('Invalid login');
                const data = await res.json();
                localStorage.setItem('token', data.token); // adjust if your backend field is different
                window.location = '/jobs';
            })
            .catch(() => setError("Login failed"));

    }

    return (
        <form onSubmit={handleSubmit}>
            <input value={username} onChange={e => setUsername(e.target.value)} placeholder="Username"/>
            <input value={password} onChange={e => setPassword(e.target.value)} type="password" placeholder="Password"/>
            <button type="submit">Login</button>
            {error && <div style={{ color: 'red' }}>{error}</div>}
        </form>
    )
}