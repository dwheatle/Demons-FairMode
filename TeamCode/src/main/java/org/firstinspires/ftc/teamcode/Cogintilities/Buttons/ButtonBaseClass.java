package org.firstinspires.ftc.teamcode.Cogintilities.Buttons;

public abstract class ButtonBaseClass {

    private boolean currentState;
    private boolean lastState;
    private final boolean invertState;
    protected boolean controlState;


    /**
     * /////////////////////////// CONSTRUCTOR \\\\\\\\\\\\\\\\\\\\\\\\\\\
     * Create a button object having the initialValue and set the output behavior for
     * normal or inverted.
     *
     * @param initialValue Initial boolean value of the button
     * @param invertOutput - Invert the output value of the button
     **/
    public ButtonBaseClass(boolean initialValue, boolean invertOutput) {
        currentState = initialValue;
        invertState = invertOutput;
        lastState = currentState;
    }


    /**
     *  This function will return true when ever the physical button changes states.
     *
     *  @param buttonValue Value of the physical button being monitored
     */
    protected boolean buttonStateChanged(boolean buttonValue) {
        lastState = currentState;
        currentState = buttonValue;

        return currentState ^ lastState; /* XOR function, only returns true when the currentState
                                            does not equal the lastState */
    }


    /**
     * This function needs to be overridden to handle the typo of button action.  It should be
     * called once every time the runOpMode loop executes
     *
     * @param physicalButtonValue Value of the button being monitored
    */
    public void update(boolean physicalButtonValue) { }


    /**
     * Gets the current state of the button being monitored. Inverts state if the invertState
     * variable is set to true.
     *
     * @return  Current button value
    */
    public boolean state() { return (controlState ^ invertState); }
    //    public boolean state() { return !(controlState & invertState); } /* NAND function */
}

