class UserRegistrationRequest {
    constructor(firstName, lastName, birthday, email, username, password, confirmPassword, phone, gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phone = phone;
        this.gender = gender;
    }
}

export default UserRegistrationRequest;