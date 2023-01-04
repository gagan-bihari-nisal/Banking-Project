import axios from "axios"

export default new class TransactionService{
    doTransaction(token,reciever,amount ){
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        const body={
            amount:amount,
            reciever:reciever
        }
        
        return axios.post('http://localhost:1000/transactions/transaction',body,{
            headers:headers
        })
    }
}