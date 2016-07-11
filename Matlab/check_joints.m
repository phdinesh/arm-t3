function [result values ok] = check_joints(q)

    limit = [];

    joints = rad2deg(q); %angles in degrees,
    joints = joints + 360 * ceil(abs((min(joints)/360))); %to 
    joints = rem(joints, 360)

    result = joints;
    
    result(1) = rem(result(1) + 60, 360); % first angle
    
  %  disp('Result(2 )');
   % result(2)
    result(2) = rem(150 - result(2) + 360 , 360); % second angle
    
    result(3) = rem(150 + result(3) , 360); % third angle
    
    % result()
    values = result * (1023/300);
    values = (round(values));
    
    %result = result + 60; %transform angles.
    %result = rem(result, 360)
    
    maxValues = [290; 290; 250];
    minValues = [10; 10; 50];
    
    ok = sum(maxValues < result) + sum(minValues > result);
    
    if ok > 0
        ok = 0;
    else
        ok = 1;
    end
    

end