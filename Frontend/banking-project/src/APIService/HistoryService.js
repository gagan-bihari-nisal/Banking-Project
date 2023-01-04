import axios from "axios"

export default new class HistoryService{
    getAllHistory(token){
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        return axios.get('http://localhost:1000/history/history',{
            headers:headers
        })
    }
}