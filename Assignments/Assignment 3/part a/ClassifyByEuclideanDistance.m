function neighborIds = ClassifyByEuclideanDistance(TestImage, TrainingMatrix, k)

neighborIds = zeros(1,k);

numOfTrainingImages =size(TrainingMatrix,1);

dist = zeros(2,numOfTrainingImages);
%dist array contains the distances and corresponding indexes
for i=1:numOfTrainingImages
    
    singleTrainingImage = zeros(1,784);
    singleTrainingImage(1,:) = TrainingMatrix(i,2:785);
    
    dist(1,i) = EucideanDistance(TestImage,singleTrainingImage);
    dist(2,i) = i;
end

    [sortedDist correspondingIndexes]=sort(dist(1,:));
    neighborIds(1,1:k)=correspondingIndexes(1,1:k);
end
    
    
    
    
    