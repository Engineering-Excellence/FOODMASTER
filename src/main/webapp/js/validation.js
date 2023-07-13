'use strict'

const emailRegex = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;
const passwordRegex = /(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const contactRegex = /^[+]?[(]?\d{2,3}[)]?-\d{3,4}-\d{4,6}$/

const checkValidate = (input, reg) => {
    return input.match(reg);
}
