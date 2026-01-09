package frc.robot.commands;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.estimator.PoseEstimator;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.generated.TunerConstants;

@Logged
public class AimAtTarget extends Command {
    private final CommandSwerveDrivetrain m_drivetrain;
    private final SwerveRequest.RobotCentric m_alignRequest;

    boolean targetVisible = false;
    double targetYaw = 0;
    double targetpitch = 0;

    double VISION_TURN_kP = 0.1;
    double Vision_Range_kP = 0.3;

    double rangeOffset = 16;

    double turn = 0;
    double range = 0;

    // PhotonCamera camera = new PhotonCamera("InnoMaker_OV9782(Apriltags)");
    PhotonCamera camera = new PhotonCamera("Arducam_OV9281_(Front_Cam)");

    PhotonPoseEstimator poseEstimator = new PhotonPoseEstimator(TunerConstants.kTagLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, TunerConstants.kRobotToCam);


    public AimAtTarget(CommandSwerveDrivetrain drivetrain) {
        m_drivetrain = drivetrain;
        m_alignRequest = new SwerveRequest.RobotCentric()
            .withDriveRequestType(DriveRequestType.Velocity);
        addRequirements( m_drivetrain);
    }

    @Override
    public void initialize() {
        // Initialization code if needed
        camera.setPipelineIndex(1);

    }

    @Override
    public void execute() {
        var results = camera.getAllUnreadResults();
        if (!results.isEmpty()) {
            // Camera processed a new frame since last
            // Get the last one in the list.
            var result = results.get(results.size() - 1);
            if (result.hasTargets()) {
                // At least one AprilTag was seen by the camera
                for (var target : result.getTargets()) {
                    // if (target.getFiducialId() == 11) {
                    //     // Found Tag 7, record its information
                    targetYaw = target.getYaw();
                    targetpitch = target.getPitch();
                    targetVisible = true;

                    var MultitagResult = result.getMultiTagResult();
                    var VisionPoseEstimate = MultitagResult.get().estimatedPose.best;
                    
                }
            }
            else {
                targetYaw = 0;
                targetpitch = 0;
            }
        }
        turn = targetYaw * VISION_TURN_kP;
        range = (targetpitch + rangeOffset) * Vision_Range_kP;

        SmartDashboard.putNumber("targetPitch", targetpitch);
        SmartDashboard.putNumber("range", range);
        SmartDashboard.putNumber("targetYaw", targetYaw);
        SmartDashboard.putNumber("turn", turn);


        m_drivetrain.setControl(
        m_alignRequest.withVelocityX(range) // Drive forward with negative Y (forward)
            .withVelocityY(0) // Drive left with negative X (left)
            .withRotationalRate(-turn) // Drive counterclockwise with negative X (left)
        );
    }

    @Override
    public boolean isFinished() {
        return Math.abs(turn) < 0.05
        && Math.abs(range) < 0.05;
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.setControl(
            m_alignRequest.withVelocityX(0) // Drive forward with negative Y (forward)
                .withVelocityY(0) // Drive left with negative X (left)
                .withRotationalRate(0)); // Drive counterclockwise with negative X (left)
    }
}