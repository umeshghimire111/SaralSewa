import React from 'react';

export default function PasswordRequirements({ password }) {
  const rules = [
    { label: 'At least 6 characters', valid: (password || '').length >= 6 },
    { label: 'At least one uppercase letter (A-Z)', valid: /[A-Z]/.test(password || '') },
    { label: 'At least one lowercase letter (a-z)', valid: /[a-z]/.test(password || '') },
    { label: 'At least one number (0-9)', valid: /[0-9]/.test(password || '') },
    { label: 'At least one special symbol (@#$%^&+=)', valid: /[@#$%^&+=]/.test(password || '') },
  ];

  return (
    <div className="pwd-rules">
      <div className="pwd-rules-title">Password Requirements:</div>
      {rules.map((rule, idx) => (
        <div key={idx} className={`pwd-rule-item ${rule.valid ? 'valid' : 'invalid'}`}>
          <span>{rule.valid ? '✓' : '○'}</span>
          <span>{rule.label}</span>
        </div>
      ))}
    </div>
  );
}
