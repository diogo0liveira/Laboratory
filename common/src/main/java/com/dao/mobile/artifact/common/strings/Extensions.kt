package com.dao.mobile.artifact.common.strings

import java.text.Normalizer
import java.util.regex.Pattern

/**
 * Remove acentos da string.
 */
fun String.removeAccents(): String
{
    this.let {
        val pattern: Pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        val decomposed: String = Normalizer.normalize(it, Normalizer.Form.NFD)
        return pattern.matcher(decomposed).replaceAll("")
    }
}