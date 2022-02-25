package java01.basic.datastructure.questions;


/**
 * @author Administrator
 */
public class Pattern {
    private boolean matched = false;
    /**
     * 正则表达式
     */
    private char[] pattern;
    /**
     * 正则表达式长度
     */
    private int pLen;

    public Pattern(char[] pattern, int pLen) {
        this.pattern = pattern;
        this.pLen = pLen;
    }

    public boolean match(char[] text, int tlen) { // 文本串及长度
        matched = false;
        rmatch(0, 0, text, tlen);
        return matched;
    }

    private void rmatch(int ti, int pj, char[] text, int tLen) {
        // 如果已经匹配了，就不要继续递归了
        if (matched) {
            return;
        }
        // 正则表达式到结尾了
        if (pj == pLen) {
            // 文本串也到结尾了
            if (ti == tLen) {
                matched = true;
            }
            return;
        }

        if ('*' == pattern[pj]) {
            // *匹配任意个字符
            for (int k = 0; k <= tLen - ti; ++k) {
                rmatch(ti + k, pj + 1, text, tLen);
            }
        } else if ('?' == pattern[pj]) {
            // ?匹配0个或者1个字符
            rmatch(ti, pj + 1, text, tLen);
            rmatch(ti + 1, pj + 1, text, tLen);
        } else if (ti < tLen && pattern[pj] == text[ti]) {
            // 纯字符匹配才行
            rmatch(ti + 1, pj + 1, text, tLen);
        }
    }
}
