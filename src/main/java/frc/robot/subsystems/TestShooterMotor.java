package frc.robot.subsystems;


import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TestShooterMotor extends SubsystemBase{
    private TalonFX IntakeMotor1;
    // private TalonFX IntakeMotor2;
    private TalonFXConfiguration TalonFXConfig;
    private MotorOutputConfigs MotorOutputConfig;



    // public SparkMax Intake1Motor = new SparkMax(1, MotorType.kBrushless);
    // public SparkMax Intake2Motor = new SparkMax(2, MotorType.kBrushless);
    // SparkMaxConfig config = new SparkMaxConfig();


    public TestShooterMotor() {
        TalonFXConfig = new TalonFXConfiguration();
        MotorOutputConfig = new MotorOutputConfigs();
        MotorOutputConfig.Inverted = InvertedValue.Clockwise_Positive;
        MotorOutputConfig.NeutralMode = NeutralModeValue.Coast;
        TalonFXConfig.withMotorOutput(MotorOutputConfig);

        IntakeMotor1 = new TalonFX(11);
        IntakeMotor1.getConfigurator().apply(TalonFXConfig);

        // IntakeMotor2 = new TalonFX(22);
        // IntakeMotor2.getConfigurator().apply(TalonFXConfig);


        // config.inverted(false);
        // config.idleMode(IdleMode.kBrake);
        // Intake1Motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        // Intake2Motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        
    }

    public void IntakeMotorOneRun(double setpoint){
        IntakeMotor1.set(-setpoint);
        // IntakeMotor2.set(setpoint);
        
    }
}
