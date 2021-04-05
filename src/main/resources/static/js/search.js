const resetFilters = () => {
    let inputText = document.getElementById("key");
    let gender = document.getElementById("gender");
    let degree = document.getElementById("degree");
    let age = document.getElementById("age");
    
    inputText.value = "";
    gender.selectedIndex = 0;
    degree.selectedIndex = 0;
    age.selectedIndex = 0;
}