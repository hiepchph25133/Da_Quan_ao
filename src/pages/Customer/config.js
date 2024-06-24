// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getStorage } from 'firebase/storage'
const firebaseConfig = {
    apiKey: "AIzaSyBKAfWE6sxY6A_OsOBWA53OqBNcnCqjJtQ",
    authDomain: "shopclothes-19bcf.firebaseapp.com",
    projectId: "shopclothes-19bcf",
    storageBucket: "shopclothes-19bcf.appspot.com",
    messagingSenderId: "891422338702",
    appId: "1:891422338702:web:aabcf5aca15bc357e8a3df",
    measurementId: "G-7JE4WVDGS6"
};



const firebaseApp = initializeApp(firebaseConfig);
const storage = getStorage(firebaseApp);

export { storage, firebaseApp };


