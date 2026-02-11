package basicmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.ui.buttons.ConfirmButton;
import javassist.CtBehavior;

@SpirePatch(
        clz = CharacterSelectScreen.class,
        method = "updateButtons"
)
public class ForceDefectChosenCharacterPatch {

    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void insert(CharacterSelectScreen __instance) {
        CardCrawlGame.chosenCharacter = AbstractPlayer.PlayerClass.DEFECT;
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethod) throws Exception {
            return LineFinder.findInOrder(
                    ctMethod,
                    new Matcher.FieldAccessMatcher(
                            ConfirmButton.class, "hb"
                    )
            );
        }
    }
}