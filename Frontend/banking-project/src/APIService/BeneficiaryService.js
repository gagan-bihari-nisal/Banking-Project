import axios from "axios"

export default new class BeneficiaryService{

    showAll(token){
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        return axios.get('http://localhost:1000/beneficiary/showAll', {
            headers: headers
        }) 
    }
    addBeneficiary(beneficiary,token){
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        const body={
            firstName:beneficiary.firstName,
            lastName:beneficiary.lastName
        }
        
        return axios.post('http://localhost:1000/beneficiary/addbeneficiary',beneficiary,{
            headers:headers
        })

    }

    delete(id,token){
        const headers = {
            'Authorization': `Bearer ${token}`
        }
       
        
        return axios.delete(`http://localhost:1000/beneficiary//delete/${id}`,{
            headers:headers
        }) 
    }
}