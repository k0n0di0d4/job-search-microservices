import { useState, useEffect } from 'react';

export default function JobList() {
  const [jobs, setJobs] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const token = localStorage.getItem('token');
    fetch('http://localhost:8080/job', {
      headers: { 'Authorization': 'Bearer ' + token }
    })
    .then(async res => {
      if (res.status === 401) {
        setError('Unauthorized');
        return;
      }
      const data = await res.json();
      setJobs(data);
    })
    .catch(() => setError('Failed to load jobs'));
  }, []);

  if (error) return <div>{error}</div>;

  return (
    <ul>
      {jobs.map(job => <li key={job.id}>{job.title} - {job.company}</li>)}
    </ul>
  );
}
