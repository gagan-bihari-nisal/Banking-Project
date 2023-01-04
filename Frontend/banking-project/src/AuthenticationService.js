export default new class AuthenticationService{
    registerSuccessfulLogin(username,token){
        sessionStorage.setItem('username',username)
        sessionStorage.setItem('token',token)
     
    }

    logout(){
        sessionStorage.removeItem('username')
        sessionStorage.removeItem('token')
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem('username')
        if(user===null) return false
        return true
    }
}