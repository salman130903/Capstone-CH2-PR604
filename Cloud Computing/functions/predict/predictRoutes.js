const express = require('express');
const router = express.Router();
const admin = require('firebase-admin');

// Firebase Firestore instance
const db = admin.firestore();

router.post('/', async (req, res) => {
  try {
    // Proses prediksi
    // ...

    await db.collection('predictions').add({ result: 'Hasil prediksi' });

    res.json({ message: 'Hasil prediksi berhasil disimpan!' });
  } catch (error) {
    res.status(500).json({ error: 'Gagal melakukan prediksi.' });
  }
});

router.get('/', async (req, res) => {
  try {
    // Operasi pembacaan data prediksi dari Firebase
    // ...

    res.json({ message: 'Mengambil data prediksi berhasil!' });
  } catch (error) {
    res.status(500).json({ error: 'Gagal mengambil data prediksi.' });
  }
});

router.get('/:id', async (req, res) => {
  try {
    const { id } = req.params;
    const doc = await db.collection('predictions').doc(id).get();

    if (!doc.exists) {
      res.status(404).json({ error: 'Prediksi tidak ditemukan.' });
    } else {
      res.json({
        id: doc.id,
        data: doc.data(),
      });
    }
  } catch (error) {
    res.status(500).json({ error: 'Gagal mengambil data prediksi.' });
  }
});

router.put('/:id', async (req, res) => {
  try {
    const { id } = req.params;
    const newData = req.body;

    await db.collection('predictions').doc(id).update(newData);

    res.json({ message: `Data prediksi dengan ID ${id} berhasil diperbarui!` });
  } catch (error) {
    res.status(500).json({ error: 'Gagal memperbarui data prediksi.' });
  }
});

router.delete('/:id', async (req, res) => {
  try {
    const { id } = req.params;

    await db.collection('predictions').doc(id).delete();

    res.json({ message: `Data prediksi dengan ID ${id} berhasil dihapus!` });
  } catch (error) {
    res.status(500).json({ error: 'Gagal menghapus data prediksi.' });
  }
});

module.exports = router;
