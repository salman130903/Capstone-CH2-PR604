const express = require('express');
const bodyParser = require('body-parser');
const admin = require('firebase-admin');

const kuisionerRoutes = require('./kuisionerRoutes');
const predictRoutes = require('./predictRoutes');

const app = express();
const port = 5000;

// Initialize Firebase Admin SDK
const serviceAccount = require('./key.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

// Middleware
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Route untuk kuisioner
app.use('/kuisioner', kuisionerRoutes);

// Route untuk prediksi
app.use('/predict', predictRoutes);

// Server running
app.listen(port, () => {
  console.log(`Server berjalan pada http://localhost:${port}`);
});