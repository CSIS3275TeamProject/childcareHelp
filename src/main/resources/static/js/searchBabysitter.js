const applyFilters = () => {
    let inputText = document.getElementById("key").value;
    let gender = document.getElementById("gender").value;
    let degree = document.getElementById("degree").value;
    let uri = `/babysitter/listOfBabysitters/name=${inputText}/gender=${gender}/degree=${degree}`;
    let encorded = encodeURI(uri);
    document.getElementById("btn-apply").href=encorded;
    return false;
}


// const resetFilters = () => {
//     let inputText = document.getElementById("key");
//     let gender = document.getElementById("gender");
//     let degree = document.getElementById("degree");
//     let age = document.getElementById("age");
    
//     inputText.value = "";
//     gender.selectedIndex = 0;
//     degree.selectedIndex = 0;
//     age.selectedIndex = 0;
// }