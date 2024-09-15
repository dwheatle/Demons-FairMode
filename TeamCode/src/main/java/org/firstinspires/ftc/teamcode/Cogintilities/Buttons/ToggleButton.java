package org.firstinspires.ftc.teamcode.Cogintilities.Buttons;

public class ToggleButton extends ButtonBaseClass {

    /**
     * /////////////////////////// CONSTRUCTOR \\\\\\\\\\\\\\\\\\\\\\\\\\\
     * The value of this button will activate high when the physical button is
     * pressed and will remain high until the button is pressed again.  Setting invertOutput will
     * reverse the behavior.
     *
     * @param initialState  Initialize Button Value to True or False
     * @param invertOutput  Invert the Output State of the Button
     **/
    public ToggleButton(boolean initialState, boolean invertOutput) {
        super(initialState, invertOutput);
    }

     /**
     * This should be called once every time the runOpMode loop executes to update the value of
     * the button.
     *
     * @param physicalButtonValue Value of the button being monitored
     */
     @Override
     public void update(boolean physicalButtonValue) {

        if (buttonStateChanged(physicalButtonValue)) {
            if(physicalButtonValue) {
                controlState = !controlState;
            }
        }

    }

}
