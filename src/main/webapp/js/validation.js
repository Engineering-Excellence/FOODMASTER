'use strict'

const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
const passwordRegex = /(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const contactRegex = /^[+]?[(]?[0-9]{2,3}[)]?-[0-9]{3,4}-[0-9]{4,6}$/
const checkValidate = (input, reg) => {
    return input.match(reg);
}
