package com.quad.trivia;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TriviaDeserializerTests {

    //language=JSON
    private final String openTDBJson1 = "{\"response_code\":0,\"results\":[{\"category\":\"Entertainment: Music\",\"type\":\"multiple\",\"difficulty\":\"easy\",\"question\":\"Whose signature guitar technique is called the &quot;windmill&quot;?\",\"correct_answer\":\"Pete Townshend\",\"incorrect_answers\":[\"Jimmy Page\",\"Eddie Van Halen\",\"Jimi Hendrix\"]},{\"category\":\"Entertainment: Television\",\"type\":\"multiple\",\"difficulty\":\"easy\",\"question\":\"Which of these Bojack Horseman characters is a human?\",\"correct_answer\":\"Todd Chavez\",\"incorrect_answers\":[\"Lennie Turtletaub\",\"Princess Carolyn\",\"Tom Jumbo-Grumbo\"]},{\"category\":\"Sports\",\"type\":\"multiple\",\"difficulty\":\"easy\",\"question\":\"Who won the 2016 Formula 1 World Driver&#039;s Championship?\",\"correct_answer\":\"Nico Rosberg\",\"incorrect_answers\":[\"Lewis Hamilton\",\"Max Verstappen\",\"Kimi Raikkonen\"]},{\"category\":\"Entertainment: Japanese Anime & Manga\",\"type\":\"multiple\",\"difficulty\":\"medium\",\"question\":\"Who is the creator of the manga series &quot;One Piece&quot;?\",\"correct_answer\":\"Eiichiro Oda\",\"incorrect_answers\":[\"Yoshihiro Togashi\",\"Hayao Miyazaki\",\"Masashi Kishimoto\"]},{\"category\":\"Science: Computers\",\"type\":\"multiple\",\"difficulty\":\"hard\",\"question\":\"What was the first company to use the term &quot;Golden Master&quot;?\",\"correct_answer\":\"Apple\",\"incorrect_answers\":[\"IBM\",\"Microsoft\",\"Google\"]},{\"category\":\"Entertainment: Video Games\",\"type\":\"boolean\",\"difficulty\":\"medium\",\"question\":\"The character that would eventually become Dr. Eggman was considered for the role of Sega&#039;s new flagship mascot before Sonic was.\",\"correct_answer\":\"True\",\"incorrect_answers\":[\"False\"]},{\"category\":\"Entertainment: Video Games\",\"type\":\"multiple\",\"difficulty\":\"medium\",\"question\":\"Which of the following Call of Duty games was a PS3 launch title?\",\"correct_answer\":\"Call of Duty 3\",\"incorrect_answers\":[\"Call of Duty 4: Modern Warfare\",\"Call of Duty: World at War\",\"Call of Duty: Roads to Victory\"]},{\"category\":\"General Knowledge\",\"type\":\"multiple\",\"difficulty\":\"hard\",\"question\":\"The word &quot;abulia&quot; means which of the following?\",\"correct_answer\":\"The inability to make decisions\",\"incorrect_answers\":[\"The inability to stand up\",\"The inability to concentrate on anything\",\"A feverish desire to rip one&#039;s clothes off\"]},{\"category\":\"Entertainment: Video Games\",\"type\":\"multiple\",\"difficulty\":\"easy\",\"question\":\"In World of Warcraft the default UI color that signifies Druid is what?\",\"correct_answer\":\"Orange\",\"incorrect_answers\":[\"Brown\",\"Green\",\"Blue\"]},{\"category\":\"History\",\"type\":\"multiple\",\"difficulty\":\"easy\",\"question\":\"What does the United States of America celebrate during the 4th of July?\",\"correct_answer\":\"The signing of the Declaration of Independence\",\"incorrect_answers\":[\"The anniversary of the Battle of Gettysburg\",\"The crossing of the Delaware River\",\"The ratification of the Constitution\"]}]}";
    private final String openTDBJson2 = "{\"response_code\":0,\"results\":[{\"category\":\"Entertainment: Video Games\",\"type\":\"boolean\",\"difficulty\":\"medium\",\"question\":\"DragonForce&#039;s &#039;Through the Fire and Flames&#039; is widely considered to be the hardest song in the Guitar Hero series.\",\"correct_answer\":\"True\",\"incorrect_answers\":[\"False\"]},{\"category\":\"General Knowledge\",\"type\":\"multiple\",\"difficulty\":\"medium\",\"question\":\"What does the &quot;G&quot; mean in &quot;G-Man&quot;?\",\"correct_answer\":\"Government\",\"incorrect_answers\":[\"Going\",\"Ghost\",\"Geronimo\"]},{\"category\":\"Geography\",\"type\":\"multiple\",\"difficulty\":\"medium\",\"question\":\"What are the four corner states of the US?\",\"correct_answer\":\"Utah, Colorado, Arizona, New Mexico\",\"incorrect_answers\":[\"Oregon, Idaho, Nevada, Utah\",\"Kansas, Oklahoma, Arkansas, Louisiana\",\"South Dakota, Minnesota, Nebraska, Iowa\"]},{\"category\":\"General Knowledge\",\"type\":\"multiple\",\"difficulty\":\"hard\",\"question\":\"The Swedish word &quot;Grunka&quot; means what in English?\",\"correct_answer\":\"Thing\",\"incorrect_answers\":[\"People\",\"Place\",\"Pineapple\"]},{\"category\":\"Entertainment: Video Games\",\"type\":\"multiple\",\"difficulty\":\"medium\",\"question\":\"Of the following space shooter games, which came out first?\",\"correct_answer\":\"Space Invaders\",\"incorrect_answers\":[\"Galaxian\",\"Galaga\",\"Sinistar\"]}]}";
    @Test
    void testJson1() {
        TriviaAPIResponse tar = TriviaDeserializer.deserialize(openTDBJson1);
        assertThat(tar.responseCode).isEqualTo(0);
        assertThat(tar.results.size()).isEqualTo(10);
        tar.results.forEach(tq -> {
            assertThat(tq.category()).isNotBlank();
            assertThat(tq.type()).isNotBlank();
            assertThat(tq.question()).isNotBlank();
            assertThat(tq.correct_answer()).isNotBlank();
            assertThat(tq.incorrect_answers()).isNotEmpty();
        });
        assertThat(tar.results.get(0).category()).isEqualTo("Entertainment: Music");
        assertThat(tar.results.get(0).incorrect_answers().size()).isEqualTo(3);
        assertThat(tar.results.get(9).correct_answer()).isEqualTo("The signing of the Declaration of Independence");
    }

    @Test
    void testJson2() {
        TriviaAPIResponse tar = TriviaDeserializer.deserialize(openTDBJson2);
        assertThat(tar.responseCode).isEqualTo(0);
        assertThat(tar.results.size()).isEqualTo(5);
        tar.results.forEach(tq -> {
            assertThat(tq.category()).isNotBlank();
            assertThat(tq.type()).isNotBlank();
            assertThat(tq.question()).isNotBlank();
            assertThat(tq.correct_answer()).isNotBlank();
            assertThat(tq.incorrect_answers()).isNotEmpty();
        });
    }
}
