function Weights = GradientDecentMethodUsingTanh(Weights,TrainingImages,CorrectLabels)

eta=0.05;
for epoch=1:100  
for i=1:size(TrainingImages,1)
    
    TransposedEachTrainingImage=TrainingImages(i,:)';
    %We can view it as Oi of the mathematical equation
    
    CurrentCorrectLabel=CorrectLabels(:,i);
    %We can view it as yi of the mathematical equation
    weightsOfCurrentLabel=Weights(1,2:785);
    
    weightedSum=weightsOfCurrentLabel*TransposedEachTrainingImage;
    
    weightedSum=weightedSum+Weights(1,1);
    %bias term
    
    CalculatedLabel=tanh(weightedSum);
    %We can view it as yi* of the mathematical equation
    
    error=CurrentCorrectLabel-CalculatedLabel;
    
    delta=CalculatedLabel*(1-(CalculatedLabel*CalculatedLabel))*error; 
    
    Weights(1,1)=Weights(1,1)+(eta*delta);
    
    %If there is no error then the delta will be zero hence no change will
    %be done to weight
    dWeight=eta*delta*TransposedEachTrainingImage;
    
    %Updating previous weights
    for d=2:785
       Weights(1,d)=Weights(1,d)+dWeight(d-1);
    end
end
end








