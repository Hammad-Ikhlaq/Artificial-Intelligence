Weights=TrainingByGradientDecentMethodSigmoid();

TestRawImages=csvread('MNIST/test.csv', 0, 0);
TestImages = zeros(10,784);
TestImages(1:10,1:784)=TestRawImages(1:10,2:785);
CorrectTestLabels= zeros(10,1);
CorrectTestLabels(1:10)=TestRawImages(1:10);

digitArray=zeros(10,1);
for k=1:10
    for i=1:10
        TransposedEachTestImages=TestImages(k,:)';
        weightedSum=(Weights(i,2:785)*TransposedEachTestImages)+Weights(i,1);
        digitArray(i,1)=SigmoidActivationFunction(weightedSum);     
    end
        disp('Predicted label');
        disp(digitArray);
        disp('Correct label:');
        disp(CorrectTestLabels(k));
end 
% Weights=TrainingByGradientDecentMethodTanh();
% for k=1:10
%     
%     TransposedEachTestImages=TestImages(k,:)';
%     weightedSum=Weights*TransposedEachTestImages;
%     CalculatedLabel=tanh(weightedSum);
%     
%     disp('Calculated label Tanh');
%     disp(mod(weightedSum,10));
%     disp('Correct label');
%     disp(CorrectTestLabels(k));
% end 