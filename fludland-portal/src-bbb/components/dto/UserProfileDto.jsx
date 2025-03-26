class UserProfileDto {
    constructor(userId, username, firstName, lastName, dateOfBirth, gender, phoneNumber, email, profileURL, profilePictureURLs) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profileURL = profileURL;
        this.profilePictureURLs = profilePictureURLs;
    }
}