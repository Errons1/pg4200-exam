package ex01;

public class Ex01 {

    public String regexPartA(){
        return ("[/\\w]*/test[/\\w]*Test.(java|cpp|kt){1}");
    }

    /*
    *
    * [/\w]*                = Need to start with sign / and followed up by any letter and number x amount of times.
    * /test                 = Need atleast one word "/test" in the folder URL.
    * [/\w]*                = Need to start with sign / and followed up by any letter and number x amount of times.
    * Test.                 = All files ends with Test. followd up by filtype.
    * (java|cpp|kt){1}      = Accepted files are java or cpp or kt. and shows only once.
    *
    * */


//    In the exam paper its sais I should give it name "regexPartA" but that already exist, therfor naming it "regexPartB".
    public String regexPartB(){
        return ("@[\\w]+:[\\w ]*(([Pp][Rr][Oo][Gg][Rr][Aa][Mm][Mm][Ii][Nn][Gg]|" +
                "[Pp][Rr][Oo][Gg][Rr][Aa][Mm][Mm][Ee][Rr][Ii][Nn][Gg])|" +
                "([Pp][Gg][a-zA-Z]{0,1}[0-9]{3,4}))+" +
                "[\\w ]*\\?");
    }

    /*
    *                                                               Note: I belive username can have numbers in them. and that why I wrote \w
    * @[\w]+:                                                       = Must start with "@" followed by username with min 1 sign and ends with ":"
    * [\w ]*                                                        = Any word a-zA-Z0-9_ and whitepace can be in here.
    * (([Pp][Rr][Oo][Gg][Rr][Aa][Mm][Mm][Ii][Nn][Gg]|               = Must include word "programming" and takes account for capital letter OR
    * [Pp][Rr][Oo][Gg][Rr][Aa][Mm][Mm][Ee][Rr][Ii][Nn][Gg])|        = programmering as well. If non of these word are here search for:
    * ([Pp][Gg][a-zA-Z]{0,1}[0-9]{3,4}))+                           = Lecture code with PG+maby one letter and 3-4 digits.
    * [\w ]*\?                                                      = Any word a-zA-Z0-9_ and whitepace can be in here. And ends on a ?
    *
    * */
}
