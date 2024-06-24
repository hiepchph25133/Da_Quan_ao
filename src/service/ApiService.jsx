import axios from 'axios';

const host = "https://vietnam-administrative-division-json-server-swart.vercel.app/";


const getProvinces = (depth) => {
    return axios.get(`${host}province?depth=${depth}`)
        .then(response => response.data)
        .catch(error => {
            console.error('Lỗi khi lấy dữ liệu địa chỉ:', error);
            throw error;
        });
};

// const getDistrictsByCity = (cityCode, depth) => {
//     return axios.get(`${host}district`)
//         .then(response => {
//             // Filter districts based on cityCode
//             const filteredDistricts = response.data.filter(district => district.idProvince === cityCode);
//             return filteredDistricts;
//         })
//         .catch(error => {
//             console.error('Lỗi khi lấy dữ liệu quận huyện:', error);
//             throw error;
//         });
// };
const getDistrictsByCity = (cityCode) => {
    return axios.get(`${host}district`)
        .then(response => {
            // Filter districts based on cityCode
            const filteredDistricts = response.data.filter(district => district.idProvince === cityCode);
            return filteredDistricts;
        })
        .catch(error => {
            console.error('Lỗi khi lấy dữ liệu quận huyện:', error);
            throw error;
        });
};

// const getWardsByDistrict = (districtCode) => {
//     return axios.get(`${host}commune?districtCode=${districtCode}`)
//         .then(response => response.data)
//         .catch(error => {
//             console.error('Lỗi khi lấy dữ liệu phường/xã:', error);
//             throw error;
//         });
// };

const getWardsByDistrict = (districtCode) => {
    return axios.get(`${host}commune`)
        .then(response => {
            const filteredWards = response.data.filter(ward => ward.idDistrict === districtCode);
            return filteredWards;
        })
        .catch(error => {
            console.error('Lỗi khi lấy dữ liệu phường/xã:', error);
            throw error;
        });
};

export {
    getProvinces, getDistrictsByCity, getWardsByDistrict,
};