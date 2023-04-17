const express = require('express');
const bodyParser = require('body-parser');

const app = express();

app.use(bodyParser.json());

app.post('http://localhost:8081/subscription/add', (req, res) => {
  // get the form data from the request body
  const { name, email, userType, company, appType } = req.body;

  // perform form sanitization here

  // store the data in the Redux store or database here

  // send a response back to the client
  res.json({ success: true });
});

app.listen(8081, () => {
  console.log('Server started on port 8081');
});

