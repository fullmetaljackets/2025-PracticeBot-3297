// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.estimation.VisionEstimation;
import org.photonvision.proto.Photon;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.generated.TunerConstants;

public class Vision_sub extends SubsystemBase {
  PhotonCamera camera = new PhotonCamera("Arducam_OV9281_(Front_Cam)");
  public double distanceToHub = 0;
  double TagID = 0;

  double pitchOffset = 3.3;

  public double getDistanceToHub(){
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
                TagID = target.getFiducialId();
                if (TagID == 11){
                  Rotation2d angleToGoal = Rotation2d.fromDegrees(TunerConstants.FrontCamMountAngle)
                  .plus(Rotation2d.fromDegrees(target.getPitch() - pitchOffset));
            
                  distanceToHub = (TunerConstants.HubTagHight - TunerConstants.FrontCamHight) / angleToGoal.getTan();

                  SmartDashboard.putNumber("angleToGoal", target.getPitch() - pitchOffset);
                  SmartDashboard.putNumber("Hub&CamHightDif", TunerConstants.HubTagHight - TunerConstants.FrontCamHight);
                  SmartDashboard.putNumber("DistToHub", distanceToHub);
                  }
                }


        }
    }
    // if (TagID == 11){
    //   Rotation2d angleToGoal = Rotation2d.fromDegrees(TunerConstants.FrontCamMountAngle)
    //   .plus(Rotation2d.fromDegrees(target.getPitch()));

    //   distanceToHub = (TunerConstants.HubTagHight - TunerConstants.FrontCamHight) / angleToGoal.getTan();

    // }
    // var results = camera.getAllUnreadResults();
    // var result = results.get(results.size() - 1);
    // var target = result.getBestTarget();
    // TagID = target.getFiducialId();

    // if (TagID == 11){
    //     Rotation2d angleToGoal = Rotation2d.fromDegrees(TunerConstants.FrontCamMountAngle)
    //     .plus(Rotation2d.fromDegrees(target.getPitch()));
  
    //     distanceToHub = (TunerConstants.HubTagHight - TunerConstants.FrontCamHight) / angleToGoal.getTan();
    //   }

    return distanceToHub;
  }
}