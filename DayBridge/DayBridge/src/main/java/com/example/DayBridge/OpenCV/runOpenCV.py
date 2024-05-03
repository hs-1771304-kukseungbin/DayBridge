import cv2
import sys
import numpy as np
import os

def find_most_similar_image(target_image_path, images_folder_path):
    target_image = cv2.imread(target_image_path)

    # 대상 이미지가 올바르게 읽혔는지 확인
    if target_image is None:
        print("대상 이미지를 읽을 수 없습니다.")
        return

    target_hist = cv2.calcHist([target_image], [0, 1, 2], None, [8, 8, 8], [0, 256, 0, 256, 0, 256])
    cv2.normalize(target_hist, target_hist, 0, 255, cv2.NORM_MINMAX)

    best_score = float('inf')
    best_image = None

    for image_name in os.listdir(images_folder_path):
        image_path = os.path.join(images_folder_path, image_name)
        image = cv2.imread(image_path)

        if image is None:
            print(f"{image_name} 이미지를 읽을 수 없습니다.")
            continue

        image_hist = cv2.calcHist([image], [0, 1, 2], None, [8, 8, 8], [0, 256, 0, 256, 0, 256])
        cv2.normalize(image_hist, image_hist, 0, 255, cv2.NORM_MINMAX)

        score = cv2.compareHist(target_hist, image_hist, cv2.HISTCMP_CHISQR)
        if score < best_score:
            best_score = score
            best_image = image_name

    print(best_image)

if __name__ == "__main__":
    target_image_path = sys.argv[1]
    images_folder_path = sys.argv[2]
    find_most_similar_image(target_image_path, images_folder_path)

# import cv2
# import numpy as np
# import os
# import sys
#
#
# def find_similar_image(input_image_path, images_folder):
#     input_image = cv2.imread(input_image_path, cv2.IMREAD_GRAYSCALE)
#     input_image_hist = cv2.calcHist([input_image], [0], None, [256], [0, 256])
#
#     # 유사도를 저장할 변수와 가장 유사한 이미지의 경로 초기화
#     min_diff = float('inf')
#     similar_image_path = None
#
#     # 폴더 내의 모든 이미지와 비교
#     for image_name in os.listdir(images_folder):
#         # 이미지 경로 구성
#         image_path = os.path.join(images_folder, image_name)
#
#         # 같은 이미지는 비교하지 않음
#         if image_path == input_image_path:
#             continue
#
#         # 이미지 읽기 및 히스토그램 계산
#         image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
#         image_hist = cv2.calcHist([image], [0], None, [256], [0, 256])
#
#         # 히스토그램 비교를 이용하여 차이 계산
#         diff = cv2.compareHist(input_image_hist, image_hist, cv2.HISTCMP_CHISQR)
#
#         # 가장 차이가 적은 이미지 업데이트
#         if diff < min_diff:
#             min_diff = diff
#             similar_image_path = image_path
#
#     return similar_image_path
#
#
# if __name__ == "__main__":
#     input_image_path = sys.argv[1]
#     images_folder = sys.argv[2]  # 비교할 이미지들이 있는 폴더 경로
#     result = find_similar_image(input_image_path, images_folder)
#     print(result)
