import DOMPurify from 'dompurify';
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { addSubscription } from '../redux/actions';
import axios from 'axios';

const SubscriptionForm = () => {
  const dispatch = useDispatch();

  const [formData, setFormData] = useState({
    name: '',
    email: '',
    userType: '',
    company: '',
    appType: ''
  });

  const [formErrors, setFormErrors] = useState({
    name: '',
    email: '',
    userType: '',
    company: '',
    appType: ''
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();
    const sanitizedData = sanitizeForm(formData);
    if (validateForm(sanitizedData)) {
        axios.post('http://localhost:8081/subscription/add', sanitizedData)
        .then((response) => {
          dispatch(addSubscription(sanitizedData));
          alert('Subscription request submitted successfully');
        })
        .catch((error) => {
          console.log(error);
          alert('An error occurred while submitting the subscription request');
        });
      setFormData({
        name: '',
        email: '',
        userType: '',
        company: '',
        appType: ''
      });
      setFormErrors({
        name: '',
        email: '',
        userType: '',
        company: '',
        appType: ''
      });
    }

  };

  const validateForm = (formData) => {
    let isValid = true;
    let errors = {};

    if (!formData.name) {
      errors.name = 'Name is required';
      isValid = false;
    }

    if (!formData.email) {
      errors.email = 'Email is required';
      isValid = false;
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      errors.email = 'Invalid email format';
      isValid = false;
    }

    if (!formData.userType) {
      errors.userType = 'User type is required';
      isValid = false;
    }

    if (!formData.company) {
      errors.company = 'Company is required';
      isValid = false;
    }

    if (!formData.appType) {
      errors.appType = 'Application type is required';
      isValid = false;
    }

    setFormErrors(errors);
    return isValid;
  };

  const sanitizeForm = (formData) => {
    const sanitizedData = {};
    Object.keys(formData).forEach((key) => {
      sanitizedData[key] = DOMPurify.sanitize(formData[key]);
    });
    return sanitizedData;
  };

  return (
    <div>
      <form onSubmit={handleFormSubmit}>
        <div>
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleInputChange}
          />
          {formErrors.name && <span>{formErrors.name}</span>}
        </div>
        <div>
          <label>Email:</label>
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleInputChange}
          />
          {formErrors.email && <span>{formErrors.email}</span>}
        </div>
        <div>
          <label>User Type:</label>
          <select name="userType" value={formData.userType} onChange={handleInputChange}>
            <option value="">-- Select User Type --</option>
            <option value="Developer">Developer</option>
            <option value="Marketing">Marketing</option>
          </select>
          {formErrors.userType && <span>{formErrors.userType}</span>}
        </div>
        <div>
          <label>Company:</label>
          <input
        type="text"
        name="company"
        value={formData.company}
        onChange={handleInputChange}
      />
      {formErrors.company && <span>{formErrors.company}</span>}
    </div>
    <div>
      <label>Application Type:</label>
      <select name="appType" value={formData.appType} onChange={handleInputChange}>
        <option value="">-- Select Application Type --</option>
        <option value="Services">Services</option>
        <option value="WebApplication">Web Application</option>
      </select>
      {formErrors.appType && <span>{formErrors.appType}</span>}
    </div>
    <div>
      <button type="submit">Submit</button>
    </div>
  </form>
</div>
);
};

export default SubscriptionForm;