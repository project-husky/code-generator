package org.husky.codegenerator.java;

/**
 * Utilities for Javadoc content.
 *
 * @author Quentin Ligier
 **/
public class JavadocUtils {

    /**
     * Cleans the ArtDecor-generated HTML content for inclusion in a Javadoc.
     *
     * @param artDecorHtml The ArtDecor-generated HTML content.
     * @return the cleaned Javadoc content.
     */
    public static String cleanArtDecorHtml(final String artDecorHtml) {
        if (artDecorHtml == null) {
            return null;
        }
        return artDecorHtml.replace(" clear=\"none\"", "")      // empty attribute
                .replace("<text>", "")                          // unrecognized tag
                .replace("</text>", "")                         // unrecognized tag
                .replace(" title=\"\"", "")                     // empty attribute
                .replace(" target=\"\"", "")                    // empty attribute
                .replace(" class=\"\"", "")                     // empty attribute
                .replaceAll("<span.*?>", "")                    // useless tag
                .replace("</span>", "")                         // useless tag
                .replaceAll("<font.*?>", "")                    // useless tag
                .replace("</font>", "")                         // useless tag
                .replace("& ", "&amp; ")                        // escape &
                .replace("@", "{@literal @}");                  // escape @
    }
}
