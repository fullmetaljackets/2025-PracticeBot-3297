package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.TestShooterMotor;


/**
 *
 */
public class ShooterRun extends Command {

    private final TestShooterMotor s_TestShooterMotor;
    private double m_IntakeSpeed;
 

    public ShooterRun(double IntakeSpeed, TestShooterMotor subsystem) {
        m_IntakeSpeed = IntakeSpeed;

        s_TestShooterMotor = subsystem;
        addRequirements(s_TestShooterMotor);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        s_TestShooterMotor.IntakeMotorOneRun(m_IntakeSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        s_TestShooterMotor.IntakeMotorOneRun(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}