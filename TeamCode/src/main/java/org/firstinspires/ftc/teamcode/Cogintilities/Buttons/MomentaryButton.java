package org.firstinspires.ftc.teamcode.Cogintilities.Buttons;

public class MomentaryButton extends ButtonBaseClass {

    /**
     * /////////////////////////// CONSTRUCTOR \\\\\\\\\\\\\\\\\\\\\\\\\\\
     * Create a momentary button that follows the state of the physical button. Output only
     * updates when the state of the physical button changes.
     *
     * @param initialState - Initialize Button Value to True or False
     * @param invertOutput - Invert the Output State of the Button
     **/
    public MomentaryButton(boolean initialState, boolean invertOutput) {
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

        if(buttonStateChanged(physicalButtonValue)) {
            controlState = physicalButtonValue;
        }
    }
}
