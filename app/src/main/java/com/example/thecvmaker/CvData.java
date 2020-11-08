package com.example.thecvmaker;

public class CvData {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String dob;
    private String nationality;
    private String gender;
    private String language;
    private String schoolName;
    private String collegeName;
    private String percentage;
    private String passingYear;
    private String designation;
    private String organisationName;
    private String workingPeriod;
    private String interestedField;
    private String skills;
    private String hobby;
    private String strength;
    private String achievement;
    private String carrierObjective;
    private String title;
    private String period;
    private String role;
    private String description;
    private String teamSize;
    public CvData(int id, String name, String address, String phoneNumber, String emailAddress,
                   String dob,String nationality,String gender,String language,String schoolName,
                   String collegeName,String percentage,String passingYear,String designation,
                   String organisationName,String workingPeriod,String interestedField,String skills,
                   String hobby,String strength,String achievement,String carrierObjective,String title,
                   String period,String role,String description,String teamSize){
        this.id=id;
        this.name=name;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.emailAddress=emailAddress;
        this. dob=dob;
        this.nationality=nationality;
        this.gender=gender;
        this.language=language;
        this.schoolName=schoolName;
        this.collegeName=collegeName;
        this.percentage=percentage;
        this.passingYear=passingYear;
        this.designation=designation;
        this.organisationName=organisationName;
        this.workingPeriod=workingPeriod;
        this.interestedField=interestedField;
        this.skills=skills;
        this.hobby=hobby;
        this.strength=strength;
        this.achievement=achievement;
        this.carrierObjective=carrierObjective;
        this.title=title;
        this.period=period;
        this.role=role;
        this.description=description;
        this.teamSize=teamSize;

    }
    public CvData(String name, String address, String phoneNumber, String emailAddress,
                   String dob,String nationality,String gender,String language,String schoolName,
                   String collegeName,String percentage,String passingYear,String designation,
                   String organisationName,String workingPeriod,String interestedField,String skills,
                   String hobby,String strength,String achievement,String carrierObjective,String title,
                   String period,String role,String description,String teamSize){
        this.name=name;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.emailAddress=emailAddress;
        this. dob=dob;
        this.nationality=nationality;
        this.gender=gender;
        this.language=language;
        this.schoolName=schoolName;
        this.collegeName=collegeName;
        this.percentage=percentage;
        this.passingYear=passingYear;
        this.designation=designation;
        this.organisationName=organisationName;
        this.workingPeriod=workingPeriod;
        this.interestedField=interestedField;
        this.skills=skills;
        this.hobby=hobby;
        this.strength=strength;
        this.achievement=achievement;
        this.carrierObjective=carrierObjective;
        this.title=title;
        this.period=period;
        this.role=role;
        this.description=description;
        this.teamSize=teamSize;
    }
    public CvData(){

    }

    public CvData(String name, String address, String phoneNumber, String emailAddress,
                  String dob,String nationality,String gender,String language){
        this.name=name;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.emailAddress=emailAddress;
        this. dob=dob;
        this.nationality=nationality;
        this.gender=gender;
        this.language=language;
    }
    public CvData(int id,String name, String address, String phoneNumber, String emailAddress,
                  String dob,String nationality,String gender,String language){
        this.name=name;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.emailAddress=emailAddress;
        this. dob=dob;
        this.nationality=nationality;
        this.gender=gender;
        this.language=language;
    }
    public CvData(String schoolName,String collegeName,String percentage,String passingYear){
        this.schoolName=schoolName;
        this.collegeName=collegeName;
        this.percentage=percentage;
        this.passingYear=passingYear;
    }
    public CvData(int id,String schoolName,String collegeName,String percentage,String passingYear){
        this.schoolName=schoolName;
        this.collegeName=collegeName;
        this.percentage=percentage;
        this.passingYear=passingYear;
    }
    public CvData(String designation,String organisationName,String workingPeriod){
        this.designation=designation;
        this.organisationName=organisationName;
        this.workingPeriod=workingPeriod;
    }
    public CvData(int id,String designation, String organisationName,String workingPeriod){
        this.designation=designation;
        this.organisationName=organisationName;
        this.workingPeriod=workingPeriod;
    }
    public CvData(String interestedField,String skills,
                  String hobby,String strength,String achievement,String carrierObjective){
        this.interestedField=interestedField;
        this.skills=skills;
        this.hobby=hobby;
        this.strength=strength;
        this.achievement=achievement;
        this.carrierObjective=carrierObjective;
    }
    public CvData(int id,String interestedField,String skills,
                  String hobby,String strength,String achievement,String carrierObjective){
        this.interestedField=interestedField;
        this.skills=skills;
        this.hobby=hobby;
        this.strength=strength;
        this.achievement=achievement;
        this.carrierObjective=carrierObjective;
    }
    public CvData(String title,String period,String role,String description,String teamSize){
        this.title=title;
        this.period=period;
        this.role=role;
        this.description=description;
        this.teamSize=teamSize;
    }
    public CvData(int id,String title,String period,String role,String description,String teamSize){
        this.title=title;
        this.period=period;
        this.role=role;
        this.description=description;
        this.teamSize=teamSize;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getWorkingPeriod() {
        return workingPeriod;
    }

    public void setWorkingPeriod(String workingPeriod) {
        this.workingPeriod = workingPeriod;
    }

    public String getInterestedField() {
        return interestedField;
    }

    public void setInterestedField(String interestedField) {
        this.interestedField = interestedField;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getCarrierObjective() {
        return carrierObjective;
    }

    public void setCarrierObjective(String carrierObjective) {
        this.carrierObjective = carrierObjective;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(String teamSize) {
        this.teamSize = teamSize;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


}