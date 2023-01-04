import axios from "axios"

export default new class FDService{
    showAll(token){
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        return axios.get('http://localhost:1000/fd/showAll', {
            headers: headers
        }) 
    }
    createFd(fd,token){
        const headers = {
            'Authorization': `Bearer ${token}`
        }
       
        return axios.post('http://localhost:1000/fd/create',fd,{
            headers:headers
        })

    }

    break(id,token){
        const headers = {
            'Authorization': `Bearer ${token}`
        }
       
        
        return axios.delete(`http://localhost:1000/fd/breakfd/${id}`,{
            headers:headers
        }) 
    }

}