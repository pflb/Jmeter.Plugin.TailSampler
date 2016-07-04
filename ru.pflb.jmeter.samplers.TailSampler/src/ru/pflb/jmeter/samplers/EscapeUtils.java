package ru.pflb.jmeter.samplers;

import java.util.HashMap;

/**
 * Created by Smirnov_VA on 01.03.2016.
 */
public class EscapeUtils {
    public static final HashMap m = new HashMap();
    static {
        m.put(34, "quot"); // " - quotation mark (APL quote)
        m.put(38, "amp"); // & - ampersand
        m.put(39, "apos"); // ' - apostrophe (apostrophe-quote); see below
        m.put(60, "lt"); // < - less-than sign
        m.put(62, "gt"); // > - greater-than sign
        m.put(160, "nbsp"); //  - no-break space (non-breaking space)[d]
        m.put(161, "iexcl"); // ¡ - inverted exclamation mark
        m.put(162, "cent"); // ¢ - cent sign
        m.put(163, "pound"); // £ - pound sign
        m.put(164, "curren"); // ¤ - currency sign
        m.put(165, "yen"); // ¥ - yen sign (yuan sign)
        m.put(166, "brvbar"); // ¦ - broken bar (broken vertical bar)
        m.put(167, "sect"); // § - section sign
        m.put(168, "uml"); // ¨ - diaeresis (spacing diaeresis); see Germanic umlaut
        m.put(169, "copy"); // © - copyright symbol
        m.put(170, "ordf"); // ª - feminine ordinal indicator
        m.put(171, "laquo"); // « - left-pointing double angle quotation mark (left pointing guillemet)
        m.put(172, "not"); // ¬ - not sign
        m.put(173, "shy"); //  - soft hyphen (discretionary hyphen)
        m.put(174, "reg"); // ® - registered sign (registered trademark symbol)
        m.put(175, "macr"); // ¯ - macron (spacing macron, overline, APL overbar)
        m.put(176, "deg"); // ° - degree symbol
        m.put(177, "plusmn"); // ± - plus-minus sign (plus-or-minus sign)
        m.put(178, "sup2"); // ² - superscript two (superscript digit two, squared)
        m.put(179, "sup3"); // ³ - superscript three (superscript digit three, cubed)
        m.put(180, "acute"); // ´ - acute accent (spacing acute)
        m.put(181, "micro"); // µ - micro sign
        m.put(182, "para"); // ¶ - pilcrow sign (paragraph sign)
        m.put(183, "middot"); // · - middle dot (Georgian comma, Greek middle dot)
        m.put(184, "cedil"); // ¸ - cedilla (spacing cedilla)
        m.put(185, "sup1"); // ¹ - superscript one (superscript digit one)
        m.put(186, "ordm"); // º - masculine ordinal indicator
        m.put(187, "raquo"); // » - right-pointing double angle quotation mark (right pointing guillemet)
        m.put(188, "frac14"); // ¼ - vulgar fraction one quarter (fraction one quarter)
        m.put(189, "frac12"); // ½ - vulgar fraction one half (fraction one half)
        m.put(190, "frac34"); // ¾ - vulgar fraction three quarters (fraction three quarters)
        m.put(191, "iquest"); // ¿ - inverted question mark (turned question mark)
        m.put(192, "Agrave"); // À - Latin capital letter A with grave accent (Latin capital letter A grave)
        m.put(193, "Aacute"); // Á - Latin capital letter A with acute accent
        m.put(194, "Acirc"); // Â - Latin capital letter A with circumflex
        m.put(195, "Atilde"); // Ã - Latin capital letter A with tilde
        m.put(196, "Auml"); // Ä - Latin capital letter A with diaeresis
        m.put(197, "Aring"); // Å - Latin capital letter A with ring above (Latin capital letter A ring)
        m.put(198, "AElig"); // Æ - Latin capital letter AE (Latin capital ligature AE)
        m.put(199, "Ccedil"); // Ç - Latin capital letter C with cedilla
        m.put(200, "Egrave"); // È - Latin capital letter E with grave accent
        m.put(201, "Eacute"); // É - Latin capital letter E with acute accent
        m.put(202, "Ecirc"); // Ê - Latin capital letter E with circumflex
        m.put(203, "Euml"); // Ë - Latin capital letter E with diaeresis
        m.put(204, "Igrave"); // Ì - Latin capital letter I with grave accent
        m.put(205, "Iacute"); // Í - Latin capital letter I with acute accent
        m.put(206, "Icirc"); // Î - Latin capital letter I with circumflex
        m.put(207, "Iuml"); // Ï - Latin capital letter I with diaeresis
        m.put(208, "ETH"); // Ð - Latin capital letter Eth
        m.put(209, "Ntilde"); // Ñ - Latin capital letter N with tilde
        m.put(210, "Ograve"); // Ò - Latin capital letter O with grave accent
        m.put(211, "Oacute"); // Ó - Latin capital letter O with acute accent
        m.put(212, "Ocirc"); // Ô - Latin capital letter O with circumflex
        m.put(213, "Otilde"); // Õ - Latin capital letter O with tilde
        m.put(214, "Ouml"); // Ö - Latin capital letter O with diaeresis
        m.put(215, "times"); // × - multiplication sign
        m.put(216, "Oslash"); // Ø - Latin capital letter O with stroke (Latin capital letter O slash)
        m.put(217, "Ugrave"); // Ù - Latin capital letter U with grave accent
        m.put(218, "Uacute"); // Ú - Latin capital letter U with acute accent
        m.put(219, "Ucirc"); // Û - Latin capital letter U with circumflex
        m.put(220, "Uuml"); // Ü - Latin capital letter U with diaeresis
        m.put(221, "Yacute"); // Ý - Latin capital letter Y with acute accent
        m.put(222, "THORN"); // Þ - Latin capital letter THORN
        m.put(223, "szlig"); // ß - Latin small letter sharp s (ess-zed); see German Eszett
        m.put(224, "agrave"); // à - Latin small letter a with grave accent
        m.put(225, "aacute"); // á - Latin small letter a with acute accent
        m.put(226, "acirc"); // â - Latin small letter a with circumflex
        m.put(227, "atilde"); // ã - Latin small letter a with tilde
        m.put(228, "auml"); // ä - Latin small letter a with diaeresis
        m.put(229, "aring"); // å - Latin small letter a with ring above
        m.put(230, "aelig"); // æ - Latin small letter ae (Latin small ligature ae)
        m.put(231, "ccedil"); // ç - Latin small letter c with cedilla
        m.put(232, "egrave"); // è - Latin small letter e with grave accent
        m.put(233, "eacute"); // é - Latin small letter e with acute accent
        m.put(234, "ecirc"); // ê - Latin small letter e with circumflex
        m.put(235, "euml"); // ë - Latin small letter e with diaeresis
        m.put(236, "igrave"); // ì - Latin small letter i with grave accent
        m.put(237, "iacute"); // í - Latin small letter i with acute accent
        m.put(238, "icirc"); // î - Latin small letter i with circumflex
        m.put(239, "iuml"); // ï - Latin small letter i with diaeresis
        m.put(240, "eth"); // ð - Latin small letter eth
        m.put(241, "ntilde"); // ñ - Latin small letter n with tilde
        m.put(242, "ograve"); // ò - Latin small letter o with grave accent
        m.put(243, "oacute"); // ó - Latin small letter o with acute accent
        m.put(244, "ocirc"); // ô - Latin small letter o with circumflex
        m.put(245, "otilde"); // õ - Latin small letter o with tilde
        m.put(246, "ouml"); // ö - Latin small letter o with diaeresis
        m.put(247, "divide"); // ÷ - division sign (obelus)
        m.put(248, "oslash"); // ø - Latin small letter o with stroke (Latin small letter o slash)
        m.put(249, "ugrave"); // ù - Latin small letter u with grave accent
        m.put(250, "uacute"); // ú - Latin small letter u with acute accent
        m.put(251, "ucirc"); // û - Latin small letter u with circumflex
        m.put(252, "uuml"); // ü - Latin small letter u with diaeresis
        m.put(253, "yacute"); // ý - Latin small letter y with acute accent
        m.put(254, "thorn"); // þ - Latin small letter thorn
        m.put(255, "yuml"); // ÿ - Latin small letter y with diaeresis
        m.put(338, "OElig"); // Œ - Latin capital ligature oe[e]
        m.put(339, "oelig"); // œ - Latin small ligature oe[e]
        m.put(352, "Scaron"); // Š - Latin capital letter s with caron
        m.put(353, "scaron"); // š - Latin small letter s with caron
        m.put(376, "Yuml"); // Ÿ - Latin capital letter y with diaeresis
        m.put(402, "fnof"); // ƒ - Latin small letter f with hook (function, florin)
        m.put(710, "circ"); // ˆ - modifier letter circumflex accent
        m.put(732, "tilde"); // ˜ - small tilde
        m.put(913, "Alpha"); // Α - Greek capital letter Alpha
        m.put(914, "Beta"); // Β - Greek capital letter Beta
        m.put(915, "Gamma"); // Γ - Greek capital letter Gamma
        m.put(916, "Delta"); // Δ - Greek capital letter Delta
        m.put(917, "Epsilon"); // Ε - Greek capital letter Epsilon
        m.put(918, "Zeta"); // Ζ - Greek capital letter Zeta
        m.put(919, "Eta"); // Η - Greek capital letter Eta
        m.put(920, "Theta"); // Θ - Greek capital letter Theta
        m.put(921, "Iota"); // Ι - Greek capital letter Iota
        m.put(922, "Kappa"); // Κ - Greek capital letter Kappa
        m.put(923, "Lambda"); // Λ - Greek capital letter Lambda
        m.put(924, "Mu"); // Μ - Greek capital letter Mu
        m.put(925, "Nu"); // Ν - Greek capital letter Nu
        m.put(926, "Xi"); // Ξ - Greek capital letter Xi
        m.put(927, "Omicron"); // Ο - Greek capital letter Omicron
        m.put(928, "Pi"); // Π - Greek capital letter Pi
        m.put(929, "Rho"); // Ρ - Greek capital letter Rho
        m.put(931, "Sigma"); // Σ - Greek capital letter Sigma
        m.put(932, "Tau"); // Τ - Greek capital letter Tau
        m.put(933, "Upsilon"); // Υ - Greek capital letter Upsilon
        m.put(934, "Phi"); // Φ - Greek capital letter Phi
        m.put(935, "Chi"); // Χ - Greek capital letter Chi
        m.put(936, "Psi"); // Ψ - Greek capital letter Psi
        m.put(937, "Omega"); // Ω - Greek capital letter Omega
        m.put(945, "alpha"); // α - Greek small letter alpha
        m.put(946, "beta"); // β - Greek small letter beta
        m.put(947, "gamma"); // γ - Greek small letter gamma
        m.put(948, "delta"); // δ - Greek small letter delta
        m.put(949, "epsilon"); // ε - Greek small letter epsilon
        m.put(950, "zeta"); // ζ - Greek small letter zeta
        m.put(951, "eta"); // η - Greek small letter eta
        m.put(952, "theta"); // θ - Greek small letter theta
        m.put(953, "iota"); // ι - Greek small letter iota
        m.put(954, "kappa"); // κ - Greek small letter kappa
        m.put(955, "lambda"); // λ - Greek small letter lambda
        m.put(956, "mu"); // μ - Greek small letter mu
        m.put(957, "nu"); // ν - Greek small letter nu
        m.put(958, "xi"); // ξ - Greek small letter xi
        m.put(959, "omicron"); // ο - Greek small letter omicron
        m.put(960, "pi"); // π - Greek small letter pi
        m.put(961, "rho"); // ρ - Greek small letter rho
        m.put(962, "sigmaf"); // ς - Greek small letter final sigma
        m.put(963, "sigma"); // σ - Greek small letter sigma
        m.put(964, "tau"); // τ - Greek small letter tau
        m.put(965, "upsilon"); // υ - Greek small letter upsilon
        m.put(966, "phi"); // φ - Greek small letter phi
        m.put(967, "chi"); // χ - Greek small letter chi
        m.put(968, "psi"); // ψ - Greek small letter psi
        m.put(969, "omega"); // ω - Greek small letter omega
        m.put(977, "thetasym"); // ϑ - Greek theta symbol
        m.put(978, "upsih"); // ϒ - Greek Upsilon with hook symbol
        m.put(982, "piv"); // ϖ - Greek pi symbol
        m.put(8194, "ensp"); //   - en space[d]
        m.put(8195, "emsp"); //   - em space[d]
        m.put(8201, "thinsp"); //   - thin space[d]
        m.put(8204, "zwnj"); //  - zero-width non-joiner
        m.put(8205, "zwj"); //  - zero-width joiner
        m.put(8206, "lrm"); //  - left-to-right mark
        m.put(8207, "rlm"); //  - right-to-left mark
        m.put(8211, "ndash"); // – - en dash
        m.put(8212, "mdash"); // — - em dash
        m.put(8216, "lsquo"); // ‘ - left single quotation mark
        m.put(8217, "rsquo"); // ’ - right single quotation mark
        m.put(8218, "sbquo"); // ‚ - single low-9 quotation mark
        m.put(8220, "ldquo"); // “ - left double quotation mark
        m.put(8221, "rdquo"); // ” - right double quotation mark
        m.put(8222, "bdquo"); // „ - double low-9 quotation mark
        m.put(8224, "dagger"); // † - dagger, obelisk
        m.put(8225, "Dagger"); // ‡ - double dagger, double obelisk
        m.put(8226, "bull"); // • - bullet (black small circle)[f]
        m.put(8230, "hellip"); // … - horizontal ellipsis (three dot leader)
        m.put(8240, "permil"); // ‰ - per mille sign
        m.put(8242, "prime"); // ′ - prime (minutes, feet)
        m.put(8243, "Prime"); // ″ - double prime (seconds, inches)
        m.put(8249, "lsaquo"); // ‹ - single left-pointing angle quotation mark[g]
        m.put(8250, "rsaquo"); // › - single right-pointing angle quotation mark[g]
        m.put(8254, "oline"); // ‾ - overline (spacing overscore)
        m.put(8260, "frasl"); // ⁄ - fraction slash (solidus)
        m.put(8364, "euro"); // € - euro sign
        m.put(8465, "image"); // ℑ - black-letter capital I (imaginary part)
        m.put(8472, "weierp"); // ℘ - script capital P (power set, Weierstrass p)
        m.put(8476, "real"); // ℜ - black-letter capital R (real part symbol)
        m.put(8482, "trade"); // ™ - trademark symbol
        m.put(8501, "alefsym"); // ℵ - alef symbol (first transfinite cardinal)[h]
        m.put(8592, "larr"); // ← - leftwards arrow
        m.put(8593, "uarr"); // ↑ - upwards arrow
        m.put(8594, "rarr"); // → - rightwards arrow
        m.put(8595, "darr"); // ↓ - downwards arrow
        m.put(8596, "harr"); // ↔ - left right arrow
        m.put(8629, "crarr"); // ↵ - downwards arrow with corner leftwards (carriage return)
        m.put(8656, "lArr"); // ⇐ - leftwards double arrow[i]
        m.put(8657, "uArr"); // ⇑ - upwards double arrow
        m.put(8658, "rArr"); // ⇒ - rightwards double arrow[j]
        m.put(8659, "dArr"); // ⇓ - downwards double arrow
        m.put(8660, "hArr"); // ⇔ - left right double arrow
        m.put(8704, "forall"); // ∀ - for all
        m.put(8706, "part"); // ∂ - partial differential
        m.put(8707, "exist"); // ∃ - there exists
        m.put(8709, "empty"); // ∅ - empty set (null set); see also U+8960, ⌀
        m.put(8711, "nabla"); // ∇ - del or nabla (vector differential operator)
        m.put(8712, "isin"); // ∈ - element of
        m.put(8713, "notin"); // ∉ - not an element of
        m.put(8715, "ni"); // ∋ - contains as member
        m.put(8719, "prod"); // ∏ - n-ary product (product sign)[k]
        m.put(8721, "sum"); // ∑ - n-ary summation[l]
        m.put(8722, "minus"); // − - minus sign
        m.put(8727, "lowast"); // ∗ - asterisk operator
        m.put(8730, "radic"); // √ - square root (radical sign)
        m.put(8733, "prop"); // ∝ - proportional to
        m.put(8734, "infin"); // ∞ - infinity
        m.put(8736, "ang"); // ∠ - angle
        m.put(8743, "and"); // ∧ - logical and (wedge)
        m.put(8744, "or"); // ∨ - logical or (vee)
        m.put(8745, "cap"); // ∩ - intersection (cap)
        m.put(8746, "cup"); // ∪ - union (cup)
        m.put(8747, "int"); // ∫ - integral
        m.put(8756, "there4"); // ∴ - therefore sign
        m.put(8764, "sim"); // ∼ - tilde operator (varies with, similar to)[m]
        m.put(8773, "cong"); // ≅ - congruent to
        m.put(8776, "asymp"); // ≈ - almost equal to (asymptotic to)
        m.put(8800, "ne"); // ≠ - not equal to
        m.put(8801, "equiv"); // ≡ - identical to; sometimes used for 'equivalent to'
        m.put(8804, "le"); // ≤ - less-than or equal to
        m.put(8805, "ge"); // ≥ - greater-than or equal to
        m.put(8834, "sub"); // ⊂ - subset of
        m.put(8835, "sup"); // ⊃ - superset of[n]
        m.put(8836, "nsub"); // ⊄ - not a subset of
        m.put(8838, "sube"); // ⊆ - subset of or equal to
        m.put(8839, "supe"); // ⊇ - superset of or equal to
        m.put(8853, "oplus"); // ⊕ - circled plus (direct sum)
        m.put(8855, "otimes"); // ⊗ - circled times (vector product)
        m.put(8869, "perp"); // ⊥ - up tack (orthogonal to, perpendicular)[o]
        m.put(8901, "sdot"); // ⋅ - dot operator[p]
        m.put(8968, "lceil"); // ⌈ - left ceiling (APL upstile)
        m.put(8969, "rceil"); // ⌉ - right ceiling
        m.put(8970, "lfloor"); // ⌊ - left floor (APL downstile)
        m.put(8971, "rfloor"); // ⌋ - right floor
        m.put(9001, "lang"); // 〈 - left-pointing angle bracket (bra)[q]
        m.put(9002, "rang"); // 〉 - right-pointing angle bracket (ket)[r]
        m.put(9674, "loz"); // ◊ - lozenge
        m.put(9824, "spades"); // ♠ - black spade suit[f]
        m.put(9827, "clubs"); // ♣ - black club suit (shamrock)[f]
        m.put(9829, "hearts"); // ♥ - black heart suit (valentine)[f]
        m.put(9830, "diams"); // ♦ - black diamond suit[f]

    }

    public static String escape(String str) {
        int len = str.length();
        StringBuilder builder = new StringBuilder(str.length());
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            int ascii = (int) c;
            String entityName = (String) m.get(ascii);
            if (entityName == null) {
                if (c > 0x7F) {
                    builder.append("&#");
                    builder.append(Integer.toString(c, 10));
                    builder.append(';');
                } else {
                    builder.append(c);
                }
            } else {
                builder.append('&');
                builder.append(entityName);
                builder.append(';');
            }
        }
        return builder.toString();
    }
}
