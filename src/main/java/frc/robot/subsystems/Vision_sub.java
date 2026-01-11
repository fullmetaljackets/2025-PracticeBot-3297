// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import org.photonvision.PhotonCamera;
// import org.photonvision.PhotonPoseEstimator;
// import org.photonvision.estimation.VisionEstimation;
// import org.photonvision.proto.Photon;

// import edu.wpi.first.math.Matrix;
// import edu.wpi.first.math.numbers.N1;
// import edu.wpi.first.math.numbers.N3;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// import frc.robot.generated.TunerConstants;
// import frc.robot.Vision;

// public class Vision_sub extends SubsystemBase {
//   PhotonCamera camera;
//   PhotonPoseEstimator photonEstimator;
//   private Matrix<N3, N1> curStdDevs;
//   public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

  
//   /** Creates a new Vision. */
//   public Vision_sub() {
//     camera = new PhotonCamera(TunerConstants.kCameraName);
//     PhotonPoseEstimator photonEstimator =
//             new PhotonPoseEstimator(TunerConstants.kTagLayout, PhotonPoseEstimator.PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, TunerConstants.kRobotToCam);
//       photonEstimator.setMultiTagFallbackStrategy(PhotonPoseEstimator.PoseStrategy.LOWEST_AMBIGUITY);
//   }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//     var cameraResults = camera.getLatestResult();
//     var VisionEst = photonEstimator.update(cameraResults);
//     VisionEst.ifPresent(
//         est -> {
//           // var estStdDevs = getEstimationStdDevs();

//           drivetrain.addVisionMeasurement(est.estimatedPose.toPose2d(), est.timestampSeconds, TunerConstants.kMultiTagStdDevs);
//       });

//     // drivetrain.addVisionMeasurement(VisionEst, 0);
//   }
// }
