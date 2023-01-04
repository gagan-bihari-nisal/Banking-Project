import axios from "axios"

export default new class AuthService {


    getHello() {
        // console.log("get hello")
        return axios.get('http://localhost:1000/auth/hello')
    }


    getCustomer(token) {
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        // var username=null
        return axios.get('http://localhost:1000/auth/customer', {
            headers: headers
        })

    }

    userExists(username){
        return axios.get(`http://localhost:1000/auth/${username}/exists`)
    }

    updateUser(firstName, lastName, fatherName, gender, dob, contact, token) {
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        // var username=null
        const body = {
            role: 'ROLE_USER',
            firstName: firstName,
            lastName: lastName,
            fatherName: fatherName,
            gender: gender,
            dob: dob,
            contact: contact
        }
        return axios.put('http://localhost:1000/auth/updateUser', body, {
            headers: headers
        })
    }

    registerUser(firstName, lastName, fatherName, gender, dob, contact,username,password){
        const body = {
            role: 'ROLE_USER',
            firstName: firstName,
            lastName: lastName,
            fatherName: fatherName,
            gender: gender,
            dob: dob,
            contact: contact,
            username:username,
            password:password
        }
        return axios.post('http://localhost:1000/auth/register', body)
    }

    getToken(username, password) {
        const credentials = {
            'username': username,
            'password': password
        }


        return axios.post('http://localhost:1000/auth/login', credentials)
    }
}