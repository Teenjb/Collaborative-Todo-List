const LocalStrategy = require('passport-local');
const passport = require('passport');
const db = require('../configs/db.config');

passport.serializeUser((user, done) => {
    done(null, user.username);
});

passport.deserializeUser(async (username, done) => {
    try{
        const query = `SELECT * FROM users WHERE username = '${username}'`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            done(null, result.rows[0]);
        }
    }catch(err){
        done(err,null);
    }
});

passport.use(new LocalStrategy(
    async(username, password, done) => {
        try{
            const query = `SELECT * FROM users WHERE username = '${username}'`;
            const result = await db.query(query);
            if(result.rowCount === 0){
                done(null, false);
            }else{
                const user = result.rows[0];
                if(user.password === password){
                    done(null, user);
                }else{
                    done(null, false);
                }
            }
        }catch(err){
            done(err, null);
        }
    }
));
