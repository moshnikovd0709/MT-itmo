import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test {

    private void check(String s) throws Exception {
        assertEquals(s.replaceAll("\\s+",""), Parser.parse(s).toString().substring(1));
    }

    @org.junit.Test
    public void test1() throws Exception {
        check("void test()");
    }

    @org.junit.Test
    public void test2() throws Exception {
        check("int fib(int n)");
    }

    @org.junit.Test
    public void test3() throws Exception {
        check("string to_string(int calculated)");
    }

    @org.junit.Test
    public void test4() throws Exception {
        check("veryHard_type1 very_hard_name_____1(very_hardDDD_type_2 varyhard_name_2)");
    }

    @org.junit.Test
    public void test5() throws Exception {
        check("a b(a b, a **b, a ********************************b)");
    }

    @org.junit.Test
    public void test6() throws Exception {
        check("agh String_type(int b, a **b, a *************b)");
    }

    @org.junit.Test
    public void test7() throws Exception {
        check("int a(int a, int b, char******& t)");
    }

    @org.junit.Test
    public void test8() throws Exception {
        check("char a(i u, String a, in b)");
    }

    @org.junit.Test
    public void test9() throws Exception {
        check("char a(int qu, String &a, int &b)");
    }

    @org.junit.Test
    public void test10() throws Exception {
        check("int fib(int &n)");
    }

}
