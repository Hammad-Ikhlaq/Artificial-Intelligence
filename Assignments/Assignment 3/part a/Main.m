TestImages=csvread('MNIST/test.csv', 0, 0);
TrainingImages=csvread('MNIST/train.csv', 0, 0);
correctPredictionCountByEucideanDist=0;
correctPredictionCountByCosSimilarity=0;
noOfTestImages=size(TestImages,1);

CorrectLabelArray = zeros(1,noOfTestImages);
CalculatedLabelArrayEucidean = zeros(1,noOfTestImages);
CalculatedLabelArrayCosine = zeros(1,noOfTestImages);
for i=1:noOfTestImages

singleTestImage = zeros(1,784);
singleTestImage(1,:) = TestImages(i,2:785);
%copying every dimension

k=3;
NeighboursID=ClassifyByEuclideanDistance(singleTestImage,TrainingImages,k);

Labels= zeros(1,k);
Labels(1,1:k)=TrainingImages(NeighboursID(1:k),1);
%getting corresponding labels

Majority=mode(Labels);
disp('By Euclidean Distance predicted label');
disp(Majority);
CalculatedLabelArrayEucidean(1,i) = Majority;

if(Majority==TestImages(i,1))
    correctPredictionCountByEucideanDist=correctPredictionCountByEucideanDist+1;
end

NeighboursID=ClassifyBySimilarityCosine(singleTestImage,TrainingImages,k);

Labels= zeros(1,k);
Labels(1,1:k)=TrainingImages(NeighboursID(1:k),1);
% getting corresponding labels

Majority=mode(Labels);
disp('By Similarity Cosine predicted label');
disp(Majority);
CalculatedLabelArrayCosine(1,i) = Majority;
disp('actual label');
disp(TestImages(i,1));
CorrectLabelArray(1,i) = TestImages(i,1);
if(Majority==TestImages(i,1))
    correctPredictionCountByCosSimilarity=correctPredictionCountByCosSimilarity+1;
end
end
disp('Percentage of Accuracy by Eucidean Distance');
percentage=(correctPredictionCountByEucideanDist/noOfTestImages)*100;
disp(percentage);
disp('Percentage of Accuracy by Cosine Similarity');
percentage=(correctPredictionCountByCosSimilarity/noOfTestImages)*100;
disp(percentage);
%plotconfusion(CorrectLabelArray,CalculatedLabelArrayCosine);
plotconfusion(CorrectLabelArray,CalculatedLabelArrayEucidean);

[confMat,order] = confusionmat(CorrectLabelArray,CalculatedLabelArrayEucidean);
%[confMat,order] = confusionmat(CorrectLabelArray,CalculatedLabelArrayCosine);
%%% recall
for i =1:size(confMat,1)
    recall(i)=confMat(i,i)/sum(confMat(i,:));
end
recall(isnan(recall))=[];
Recall=1;
Recall=Recall*(sum(recall)/size(confMat,1));
disp('Recall');
disp(Recall);
%%% précision
for i =1:size(confMat,1)
    precision(i)=confMat(i,i)/sum(confMat(:,i));
end
Precision=sum(precision)/size(confMat,1);
disp('Precision');
disp(Precision);
%%% F-score
F_score=2*Recall*Precision/(Precision+Recall); %%F_score=2*1/((1/Precision)+(1/Recall));
disp('F_score');
disp(F_score);
