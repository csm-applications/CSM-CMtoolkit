package Modules.PreProcessing.Impl.Enum;

/*
CC Coordinating conjunction
CD Cardinal number
DT Determiner
EX Existential there
FW Foreign word
IN Preposition or subordinating conjunction
JJ Adjective
JJR Adjective, comparative
JJS Adjective, superlative
LS List item marker
MD Modal
NN Noun, singular or mass
NNS Noun, plural
NNP Proper noun, singular
NNPS Proper noun, plural
PDT Predeterminer
POS Possessive ending
PRP Personal pronoun
PRP$ Possessive pronoun
RB Adverb
RBR Adverb, comparative
RBS Adverb, superlative
RP Particle
SYM Symbol
TO to
UH Interjection
VB Verb, base form
VBD Verb, past tense
VBG Verb, gerund or present participle
VBN Verb, past participle
VBP Verb, non­3rd person singular present
VBZ Verb, 3rd person singular present
WDT Wh­determiner
WP Wh­pronoun
WP$ Possessive wh­pronoun
WRB Wh­adverb
 */
public enum PartOfSpeechTagSelectionEnum {

    Coordinating_conjunction("CC"),
    Cardinal_number("CD"),
    Determiner("DT"),
    Existential_there("EX"),
    Foreign_word("FW"),
    Preposition_or_subordinating_conjunction("IN"),
    Adjective("JJ"),
    Adjective_Comparative("JJR"),
    Adjective_superlative("JJS"),
    List_item_marker("LS"),
    Modal("MD"),
    Noun_Singular_or_Mass("NN"),
    Noun_plural("NNS"),
    Proper_noun_singular("NNP"),
    Proper_noun_plural("NNPS"),
    Predeterminer("PDT"),
    Possessive_ending("POS"),
    Personal_pronoun("PRP"),
    Possessive_pronoun("PRP$"),
    Adverb("RB"),
    Adverb_comparative("RBR"),
    Adverb_superlative("RBS"),
    Particle("RP"),
    Symbol("SYM"),
    to("TO"),
    Interjection("UH"),
    Verb_base_form("VB"),
    Verb_past_tense("VBD"),
    Verb_gerund_or_present_participle("VBG"),
    Verb_past_participle("VBN"),
    Verb_non_3rd_person_singular_present("VBP"),
    Verb_3rd_person_singular_present("VBZ"),
    Wh_determiner("WDT"),
    Wh_pronoun("WP"),
    Possessive_wh_pronoun("WP$"),
    Wh_adverb("WRB");

    private String tag;

    PartOfSpeechTagSelectionEnum(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
