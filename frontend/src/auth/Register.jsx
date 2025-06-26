import { useState } from 'react';

export default function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [error, setError] = useState('');

  function handleSubmit(e) {
    e.preventDefault();

    fetch('http://localhost:8080/auth/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password, email })
    })
    .then(async res => {
      if (!res.ok) throw new Error('Registration failed');
      const data = await res.json();
      localStorage.setItem('token', data.token); // adjust if your backend sends a token here
      window.location = '/jobs';
    })
    .catch(() => setError("Registration failed"));
  }

  return (
    <form onSubmit={handleSubmit}>
      <input value={username} onChange={e => setUsername(e.target.value)} placeholder="Username" />
      <input value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" />
      <input value={password} onChange={e => setPassword(e.target.value)} type="password" placeholder="Password" />
      <button type="submit">Register</button>
      {error && <div style={{ color: 'red' }}>{error}</div>}
    </form>
  );
}
