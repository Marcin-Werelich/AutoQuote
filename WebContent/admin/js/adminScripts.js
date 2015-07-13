	function checkAndAddLangs(name, buttonName, selectItemId) {
        if(!document.getElementById(buttonName).disabled) {
            addAllLangsToLangSelect(selectItemId);
            enableTextBoxEditAndDisableAllOthers(name);
        }

    }

    function checkAndDeleteLangs(name, buttonName, selectItemId) {
        if(!document.getElementById(buttonName).disabled) {
            deleteAllLangsButSelected(selectItemId);
            saveTextBoxEdit(name);
        }

    }

    function addAllLangsToLangSelect(selectItemId) {
        var langs = ["ab	Abkhazian", "ab-ge	Abkhazian (*Georgia)", "om	Afaan Oromo", "om-et	Afaan Oromo (*Ethiopia)", "aa	Afar", "aa-dj	Afar (*Djibouti)", "af	Afrikaan", "af-za	Afrikaan (*South Africa)", "am	Amharic", "am-et	Amharic (*Etiopia)", "ar	Arabic", "ar-sa	Arabic (*Saudi Arabia)", "ar-dz	Arabic (Algeria)", "ar-bh	Arabic (Bahrain)", "ar-km	Arabic (Comoros)", "ar-dj	Arabic (Djibouti)", "ar-eg	Arabic (Egypt)", "ar-iq	Arabic (Iraq)", "ar-jo	Arabic (Jordan)", "ar-kw	Arabic (Kuwait)", "ar-lb	Arabic (Lebanon)", "ar-ly	Arabic (Libya)", "ar-ma	Arabic (Morocco)", "ar-om	Arabic (Oman)", "ar-ps	Arabic (Palestine)", "ar-qa	Arabic (Qatar)", "ar-sd	Arabic (Sudan)", "ar-sy	Arabic (Syria)", "ar-tn	Arabic (Tunisia)", "ar-ae	Arabic (U.A.E.)", "ar-ye	Arabic (Yemen)", "hy	Armenian", "hy-am	Armenian (*Armenia)", "as	Assamese", "as-in	Assamese (*India)", "ay	Aymara", "ay-bo	Aymara (*Bolivia)", "az	Azerbaijani", "az-az	Azerbaijani (*Azerbaijan)", "az-cyrl-az	Azerbaijani (Azerbaijan, Cyrillic)", "ba	Bashkir", "ba-ru	Bashkir (*Russia)", "eu	Basque", "eu-es	Basque (*Spain)", "be	Belarusian", "be-by	Belarusian (*Belarus)", "bn	Bengali", "bn-bd	Bengali (*Bengladesh)", "dz	Bhutani", "dz-bt	Bhutani (*Bhutan)", "bh	Bihari", "bh-in	Bihari (*India)", "bi	Bislama", "bi-vu	Bislama (*Vanuatu)", "bs	Bosnian", "bs-ba	Bosnian (*Bosnia and Herzegovina)", "br	Breton", "br-fr	Breton (*France)", "bg	Bulgarian", "bg-bg	Bulgarian (*Bulgaria)", "my	Burmese", "my-mm	Burmese (*Myanmar)", "ca	Catalan", "ca-es	Catalan (*Spain)", "zh	Chinese", "zh-cn	Chinese (*China, =Simplified)", "zh-hk	Chinese (Hong-Kong)", "zh-mo	Chinese (Macau)", "zh-sg	Chinese (Singapore)", "zh-tw	Chinese (Taiwan, =Traditional)", "zh-hans	Chinese (Simplified)", "zh-hant	Chinese (Traditional)", "kw	Cornish Gaelic", "kw-gb	Cornish Gaelic (*United Kingdom)", "co	Corsican", "co-fr	Corsican (*France)", "ht	Haitian Creole", "ht-ht	Haitian Creole (*Haiti)", "hr	Croatian", "hr-hr	Croatian (*Croatia)", "hr-ba	Croatian (Bosnia and Herzegovina)", "cs	Czech", "cs-cz	Czech (*Czech Republic)", "da	Danish", "da-dk	Danish (Denmark)", "snm	Dhivehi", "snm-mv	Dhivehi (*Maldives)", "nl	Dutch", "nl-nl	Dutch (*The Netherlands)", "nl-be	Dutch (Belgium, =Flemish)", "nl-sr	Dutch (Surinam)", "en	English", "en-us	English (*United States)", "en-au	English (Australia)", "en-bs	English (Bahamas)", "en-bb	English (Barbados)", "en-bz	English (Belize)", "en-ca	English (Canada)", "en-dm	English (Dominica)", "en-gm	English (Gambia)", "en-gh	English (Ghana)", "en-gd	English (Grenada)", "en-gy	English (Guyana)", "en-ie	English (Ireland)", "en-in	English (India)", "en-jm	English (Jamaica)", "en-ki	English (Kiribati)", "en-ls	English (Lesotho)", "en-lr	English (Liberia)", "en-mw	English (Malawi)", "en-mu	English (Mauritius)", "en-na	English (Namibia)", "en-nz	English (New Zealand)", "en-ng	English (Nigeria)", "en-pg	English (Papua New-Guinea)", "en-ph	English (Philippines)", "en-za	English (South Africa)", "en-sl	English (Sierra Leone)", "en-vc	English (St Vincent and the Grenadines)", "en-tt	English (Trinidad and Tobago)", "en-gb	English (United Kingdom)", "x-en-carib	English (Windows Caribbean)", "x-en-neutral	English (Windows Neutral)", "en-zm	English (Zambia)", "en-zw	English (Zimbabwe)", "eo	Esperanto", "et	Estonian", "et-ee	Estonian (*Estonia)", "fo	Faeroese", "fo-fo	Faeroese (*Faeroese Islands)", "fa	Farsi", "fa-ir	Farsi (*Iran)", "fj	Fijian", "fj-fj	Fijian (*Fiji)", "fi	Finnish", "fi-fi	Finnish (*Finland)", "fr	French", "fr-fr	French (*France)", "fr-be	French (Belgium)", "fr-bf	French (Burkina Faso)", "fr-cm	French (Cameroon)", "fr-ca	French (Canada, =Québécois)", "fr-td	French (Chad)", "fr-km	French (Comoros)", "fr-cg	French (Congo)", "fr-ci	French (Côte d'Ivoire)", "fr-dj	French (Djibouti)", "fr-ga	French (Gabon)", "fr-gn	French (Guinea)", "fr-ht	French (Haiti)", "fr-lu	French (Luxembourg)", "fr-ml	French (Mali)", "fr-mr	French (Mauritania)", "fr-mc	French (Monaco)", "fr-sn	French (Senegal)", "fr-ch	French (Switzerland)", "fr-tg	French (Togo)", "fr-zr	French (Zaire)", "fy	Frisian", "fy-nl	Frisian (*The Netherlands)", "gl	Galician", "gl-es	Gallegan (*Spain)", "ka	Georgian", "ka-ge	Georian (*Georgia)", "de	German", "de-de	German (*Germany)", "de-at	German (Austria)", "de-li	German (Liechtenstein)", "de-lu	German (Luxembourg)", "de-ch	German (Switzerland)", "gil	Gilbertese", "gil-ki	Gilbertese (*Kiribati)", "gil-tv	Gilbertese (Tuvalu)", "el	Greek", "el-gr	Greek (*Greece)", "el-cy	Greek (Cyprus)", "gn	Guarani", "gn-py	Guarani (*Paraguay)", "gu	Gujarati", "gu-in	Gujarati (*India)", "ha	Hausa", "ha-ng	Hausa (*Nigeria)", "he	Hebrew", "he-il	Hebrew (*Israel)", "hi	Hindi", "hi-in	Hindi (*India)", "hu	Hungarian", "hu-hu	Hungarian (*Hungarian)", "is	Icelandic", "is-is	Icelandic (*Iceland)", "id	Indonesian", "id-id	Indonesian (*Indonesia)", "ia	Interlingua", "ie	Interlingue", "iu	Inuktitut", "iu-ca	Inuktitut (*Canada)", "ik	Inupiak", "ik-us	Inupiak (*United States)", "ga	Irish Gaelic", "ga-ie	Irish Gaelic (*Ireland)", "it	Italian", "it-it	Italian (*Italy)", "it-sm	Italian (San Marino)", "it-ch	Italian (Switzerland)", "ja	Japanese", "ja-jp	Japanese (*Japan)", "jw	Javanese", "jw-id	Javanese (*Indonesia)", "kl	Kalaallisut", "kl-gl	Kalaallisut (*Greenland)", "kn	Kannada", "kn-in	Kannada (*India)", "ks	Kashmiri", "ks-in	Kashmiri (*India)", "kk	Kazakh", "kk-kz	Kazakh (*Kazakhstan)", "rw	Kinyarwanda", "rw-rw	Kinyarwanda (*Rwanda)", "ky	Kirghiz", "ky-kg	Kirghiz (*Kyrgyzstan)", "rn	Kirundi", "rn-bi	Kirundi (*Burundi)", "km	Khmer", "km-kh	Khmer (*Cambodia)", "kok	Konkani", "kok-in	Konkani (*India)", "ko	Korean", "ko-kr	Korean (*Korea)", "ko-kp	Korean (North Korea)", "lo	Lao", "lo-la	Lao (*Laos)", "lap	Lappish", "lap-no	Lappish (*Norway)", "la	Latin", "la-va	Latin (*Vatican)", "lv	Latvian", "lv-lv	Latvian (*Latvia)", "ln	Lingala", "ln-cg	Lingala (*Congo)", "lt	Lithuanian", "lt-lt	Lithuanian (*Lithuania)", "x-lt-lt-classic	Lithuanian (Lithuania, Classical)", "lb	Luxemburgian", "lb-lu	Luxemburgian (*Luxembourg)", "mk	Macedonian", "mk-mk	Macedonian (*Macedonia)", "mg	Malagasy", "mg-mg	Malagasy (*Madagascar)", "ms	Malay", "ms-my	Malay (*Malaysia)", "ms-bn	Malay (Brunei Darussalam)", "ml	Malayalam", "ml-in	Malayalam (*India)", "mt	Maltese", "mt-mt	Maltese (*Malta)", "mni	Manipuri", "mni-in	Manipuri (*India)", "gv	Manx Gaelic", "gv-gb	Manx Gaelic (*United Kingdom)", "mi	Maori", "mi-nz	Maori (*New Zealand)", "mr	Marathi", "mr-in	Marathi (*India)", "mo	Moldovan", "mo-md	Moldovan (*Moldova)", "mn	Mongolian", "mn-mn	Mongolian (*Mongolia)", "na	Nauruan", "na-nr	Nauruan (*Nauru)", "nv	Navajo", "nv-us	Navajo (*United States)", "ne	Nepali", "ne-np	Nepali (*Nepal)", "ne-in	Nepali (India)", "no	Norwegian", "no-no	Norwegian (*Norway)", "nn-no	Norwegian Nynorsk (*Norway)", "oc	Occitan", "oc-fr	Occitan (*France)", "or	Oriya", "or-in	Oriya (*India)", "ps	Pashto", "ps-af	Pashto (*Afghanistan)", "pl	Polish", "pl-pl	Polish (*Poland)", "pt	Portuguese", "pt-br	Portuguese (*Brazil)", "pt-ao	Portuguese (Angola)", "pt-cv	Portuguese (Cape Verde)", "pt-gw	Portuguese (Guinea-Bissau)", "pt-mz	Portuguese (Mozambique)", "pt-pt	Portuguese (Portugal)", "pa	Punjabi", "pa-in	Punjabi (*India)", "qu	Quechua", "qu-pe	Quechua (*Peru)", "ro	Romanian", "ro-ro	Romanian (*Romania)", "ro-md	Romanian (Moldova)", "rm	Raetho-Roman", "rm-ch	Raetho-Roman (*Switzerland)", "ru	Russian", "ru-ru	Russian (*Russia)", "ru-md	Russian (Moldova)", "sa	Sanskrit", "sa-in	Sanskrit (*India)", "sd	Sindhi", "sd-in	Sindhi (*India)", "si	Singhalese", "si-lk	Singhalese (*Sri-Lanka)", "sk	Slovak", "sk-sk	Slovak (*Slovakia)", "sl	Slovenian", "sl-si	Slovenian (*Slovenia)", "sm	Samoan", "sm-ws	Samoan (*Western Samoa)", "sq	Albanian", "sq-al	Albanian (*Albania)", "sr	Serbian", "sr-cyrl-sp	Serbian (*Serbia)", "sr-cyrl-ba	Serbian (Bosnia and Herzegovina)", "ss	Siswati", "ss-sz	Siswati (*Swaziland)", "ss-za	Siswati (South-Africa)", "st	Sesotho", "st-ls	Sesotho (*Lesotho)", "sv	Swedish", "sv-se	Swedish (*Sweden)", "sv-ax	Swedish (Åland Islands)", "sv-fi	Swedish (Finland)", "sw	Swahili", "sw-ke	Swahili (*Kenya)", "syr	Syriac", "gd	Scottish Gaelic", "gd-gb	Scottish Gaelic (*United Kingdom)", "tet	Tetum", "tet-tl	Tetum (*Timor-Leste)", "yo	Yoruba", "yo-ng	Yoruba (*Nigeria)", "yi	Yiddish", "yi-il	Yiddish (*Israel)", "vi	Vietnamese", "vi-vn	Vietnamese (*Vietnam)", "wo	Wolof", "wo-sn	Wolof (*Senegal)", "wen	Sorbian", "wen-de	Sorbian (*Germany)", "x-win-neutral	Windows Neutral", "x-win-default	Windows Neutral (Default)", "x-win-system	Windows Neutral (System)", "bo	Tibetan", "bo-zh	Tibetan (*China)", "cy	Welsh", "cy-gb	Welsh (*United Kingdom)", "es	Spanish", "es-es	Spanish (*Spain)", "es-ar	Spanish (Argentina)", "es-bo	Spanish (Bolivia)", "es-cl	Spanish (Chile)", "es-co	Spanish (Colombia)", "es-cr	Spanish (Costa Rica)", "es-cu	Spanish (Cuba)", "es-do	Spanish (Dominican Republic)", "es-ec	Spanish (Ecuador)", "es-gq	Spanish (Equatorial Guinea)", "es-gt	Spanish (Guatemala)", "es-hn	Spanish (Honduras)", "es-mx	Spanish (Mexico)", "es-ni	Spanish (Nicaragua)", "es-pa	Spanish (Panama)", "es-pe	Spanish (Peru)", "es-pr	Spanish (Puerto Rico)", "es-py	Spanish (Paraguay)", "es-sv	Spanish (El Salvador)", "es-uy	Spanish (Uruguay)", "es-ve	Spanish (Venezuela)", "es-419	Spanish (Latin American & the Caribbean)", "es-x-modern	Spanish (Modern Sort)", "th	Thai", "th-th	Thai (*Thailand)", "tr	Turkish", "tr-tr	Turkish (*Turkey)", "tr-cy	Turkish (Cyprus)", "uk	Ukrainian", "uk-ua	Ukrainian (*Ukraine)", "ur	Urdu", "uk-pk	Urdu (*Pakistan)", "ug	Uighur", "ug-cn	Uighur (*China)", "zu	Zulu", "zu-za	Zulu (*south Africa)"];
        var selectItem = document.getElementById(selectItemId);
        var tempStringSplit;

        for(var i = 0; i < langs.length; i ++) {
            tempStringSplit = langs[i].split("\t");
            selectItem.appendChild(createOptionElement(tempStringSplit[0],tempStringSplit[1]));
        }

    }

    function deleteAllLangsButSelected(selectItemId) {
        var selectItem = document.getElementById(selectItemId);
        var selectedOption = selectItem.options[selectItem.selectedIndex];
        var selectId = selectedOption.id;
        var selectText = selectedOption.text;

        while (selectItem.hasChildNodes()) {
            selectItem.removeChild(selectItem.firstChild);
        }

        selectItem.appendChild(createOptionElement(selectId, selectText));

    }

    function enableSelectEditAndDisableAllOthers(name){
        var editButton = document.getElementById(name + '_edit');
        var saveButton = document.getElementById(name + '_save');
        var textBox = document.getElementById(name + '_text');
        var allEditButtons = document.getElementsByName("editButton");

        editButton.disabled = true;
        saveButton.disabled = false;
        textBox.disabled = false;

        for (var i = 0; i < allEditButtons.length; i++) {
            if(!allEditButtons[i].id.match(editButton.id)) {
                allEditButtons[i].disabled = true;
            }
        }

    }

    function saveSelectBoxEdit(name){
        var editButton = document.getElementById(name + '_edit');
        var saveButton = document.getElementById(name + '_save');
        var textBox = document.getElementById(name + '_text');

        editButton.disabled = false;
        saveButton.disabled = true;
        textBox.disabled = true;
    }

    function createSelectOptionElement(idValue, textValue) {
        var selectOption = document.createElement("OPTION");
        selectOption.setAttribute("id", idValue);
        selectOption.setAttribute("name", "option");
        selectOption.appendChild(document.createTextNode(textValue));
        return selectOption;
    }
    
    function addNewLanguage(idValue, textValue) {
        var newLangFullElement = document.createElement("p");
        var newLangTextBox = document.createElement("input");
        var newLangTextboxText = document.createTextNode(textValue);
        var newLangDeleteButton = document.createElement("button");
        var newLangDeleteButtonText = document.createTextNode("Remove Language");

        newLangFullElement.setAttribute("id", idValue);
        newLangFullElement.setAttribute("name", "newLang");

        newLangDeleteButton.setAttribute("onclick", "deleteNewLanguage(" + idValue + ")");

        newLangFullElement.appendChild(newLangTextBox);
        newLangFullElement.appendChild(newLangDeleteButton);
        document.appendChild(newLangFullElement);
    }

    function deleteNewLanguage(idValue) {
        document.removeChild(document.getElementById(idValue));
    }
