const bcrypt = require('bcryptjs');
const { check } = require('express-validator');

async function hashPassword(password) {
    return await bcrypt.hash(password, 10);
}

async function comparePassword(password, hash) {
    return await bcrypt.compare(password, hash);
}

const spaceCheckerUsername = check('username')
  .custom(value => !/\s/.test(value))
  .withMessage('No spaces are allowed in the username');

const spaceCheckerPassword = check('password')
  .custom(value => !/\s/.test(value))
  .withMessage('No spaces are allowed in the password');

module.exports = {
    hashPassword,
    comparePassword,
    spaceChecker: [spaceCheckerUsername, spaceCheckerPassword]
};