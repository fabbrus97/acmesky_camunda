lines = ""

with open("voli.txt", "r") as f:
        lines = f.readlines()

with open("voli.txt", "r") as f:
        for line in lines:
            volo = line.split()
            data_volo = volo[2]+" "+volo[3]+" "+volo[4]
            date_time_obj = datetime.datetime.strptime(data_volo, '%d/%m/%Y %H:%M %p')
            print(date_time_obj)
