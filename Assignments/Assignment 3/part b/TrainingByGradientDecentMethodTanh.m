SiTestRawImages=csvread('MNIST/test.csv', 0, 0);
TestImages = zeros(10,784);
TestImages(1:10,1:784)=TestRawImages(1:10,2:785);
CorrectTestLabels= zeros(10,1);
CorrectTestLabels(1:10)=TestRawImages(1:10);

File=csvread('MNIST/train.csv', 0, 0);

Weights=zeros(10,785);
for i=1:10
for j=2:785
Weights(i,j)= rand(1,1) -0.5;
end
end
Weights(:,1)=1; %bias term initailize

noOfTrainingImages =7000;

TrainingImages = zeros(noOfTrainingImages,784);
TrainingImages(1:noOfTrainingImages,1:784)=File(1:noOfTrainingImages,2:785);
TrLab= zeros(noOfTrainingImages,1);
TrLab(1:noOfTrainingImages)=File(1:noOfTrainingImages);

CorrectLabels= zeros(10,noOfTrainingImages);

for i=1:10
   k=1;
   for j=1:noOfTrainingImages
      if(TrLab(j,1)==(i-1))
          CorrectLabels(i,k)=1;
          k=k+1;
      else 
          CorrectLabels(i,k)=-1;
          k=k+1;
      end
   end
end

predictions=zeros(1,10);

noOfTestImages =10;
for k=1:noOfTestImages
for i=1:10
    Weights(i,:)=GradientDecentMethodUsingTanh(Weights(i,:),TrainingImages,CorrectLabels(i,:));
    
    TransposedEachTestImage=TestImages(k,:)';
    
    weightsOfCurrentLabel=Weights(i,2:785);
    
    weightedSum=weightsOfCurrentLabel*TransposedEachTestImage;
    
    weightedSum=weightedSum+Weights(i,1);
    %bias term
    
    predictions(1,i)=SigmoidActivationFunction(weightedSum);
    
end
CurrentCorrectLabel=CorrectTestLabels(k,1);
disp('Prediction');
disp(predictions);
disp('Correct Label');
disp(CurrentCorrectLabel);
end