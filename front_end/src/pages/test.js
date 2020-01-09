let fmtName = (name) => {
    let num = name.indexOf(".");
    if (num != -1)
        name = name.substr(0, num);
    let fullName = name.split("-");
    if (fullName.length > 1) {
        name = fullName[1];
    }
    return name.trim();
}
let name = " dad-songname.da"
console.log(fmtName(name))